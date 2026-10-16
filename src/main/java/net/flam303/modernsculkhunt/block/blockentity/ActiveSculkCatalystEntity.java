package net.flam303.modernsculkhunt.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ActiveSculkCatalystEntity extends BlockEntity {
    public static final float MAX_HEALTH = 50.0f;
    public float currentHealth = MAX_HEALTH;
    public int healTimer = 0;
    public static final int HEAL_INTERVAL = 600;

    public ActiveSculkCatalystEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ACTIVE_SCULK_CATALYST_BE.get(), pos, blockState);
    }

    public void takeDamage(Level level, BlockPos pos, float damage) {
        if (damage <= 0) {
            damage = 1.0f;
        }

        currentHealth -= damage;
        healTimer = 0;
        if (currentHealth <= 0.0f) {
            level.destroyBlock(pos, false);
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ActiveSculkCatalystEntity blockEntity) {
        if (blockEntity.currentHealth < MAX_HEALTH) {
            blockEntity.healTimer ++;

            if (blockEntity.healTimer >= HEAL_INTERVAL) {
                blockEntity.currentHealth = MAX_HEALTH;
                blockEntity.healTimer = 0;
                blockEntity.setChanged();
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putFloat("BlockHealth", this.currentHealth);
        tag.putInt("HealTimer", this.healTimer);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.currentHealth = tag.getFloat("BlockHealth");
        this.healTimer = tag.getInt("HealTimer");
    }

}
