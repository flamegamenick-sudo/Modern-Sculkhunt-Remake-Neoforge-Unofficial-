package net.flam303.modernsculkhunt.block.custom;

import net.flam303.modernsculkhunt.block.blockentity.ActiveSculkCatalystEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ActiveSculkCatalystBlock extends BaseEntityBlock {

    public ActiveSculkCatalystBlock(Properties properties) {
        super(properties.destroyTime(-1.0F).explosionResistance(1200.0F));
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

}
