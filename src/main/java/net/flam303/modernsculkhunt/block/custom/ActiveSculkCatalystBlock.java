package net.flam303.modernsculkhunt.block.custom;

import com.mojang.serialization.MapCodec;
import net.flam303.modernsculkhunt.block.blockentity.ActiveSculkCatalystEntity;
import net.flam303.modernsculkhunt.block.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ActiveSculkCatalystBlock extends BaseEntityBlock {

    private static final MapCodec<ActiveSculkCatalystBlock> CODEC = simpleCodec(ActiveSculkCatalystBlock::new);

    public ActiveSculkCatalystBlock(Properties properties) {
        super(properties.destroyTime(-1.0F).explosionResistance(1200.0F));
        this.registerDefaultState(this.stateDefinition.any().setValue(SculkCatalystBlock.PULSE, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(net.minecraft.world.level.block.state.StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(net.minecraft.world.level.block.SculkCatalystBlock.PULSE);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ActiveSculkCatalystEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide? null : createTickerHelper(type,
                ModBlockEntities.ACTIVE_SCULK_CATALYST_BE.get(),
                ActiveSculkCatalystEntity::serverTick);
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return 0.0f;
    }

}
