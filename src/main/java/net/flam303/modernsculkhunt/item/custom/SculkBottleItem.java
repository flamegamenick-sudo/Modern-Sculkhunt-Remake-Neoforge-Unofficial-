package net.flam303.modernsculkhunt.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class SculkBottleItem extends HoneyBottleItem {

    public SculkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        if (!level.isClientSide) {
            BlockPos blockPos = livingEntity.blockPosition();

            // replace with ModBlocks.ACTIVE_SCULK_CATALYST.get().defaultBlockState()
            level.setBlockAndUpdate(blockPos, Blocks.AMETHYST_BLOCK.defaultBlockState());

            livingEntity.hurt(level.damageSources().outOfBorder(), Float.MAX_VALUE);

        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
