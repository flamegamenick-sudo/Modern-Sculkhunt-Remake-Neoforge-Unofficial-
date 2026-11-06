package net.flam303.modernsculkhunt.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SculkBottleItem extends HoneyBottleItem {
    public SculkBottleItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        if (!level.isClientSide) {
            BlockPos blockPos = entityLiving.blockPosition();

            ItemStack newStack = Items.GLASS_BOTTLE.getDefaultInstance();
            stack.shrink(1);

            if (entityLiving instanceof Player player) {
                boolean success = player.getInventory().add(newStack);
                if (!success) {
                    player.drop(newStack, false);
                }
            }

            BlockState state = Blocks.AMETHYST_BLOCK.defaultBlockState();
            level.setBlockAndUpdate(blockPos, state);

            DamageSource damageSource = level.damageSources().outOfBorder();
            entityLiving.hurt(damageSource, Float.MAX_VALUE);
        }
        return stack;
    }
}
