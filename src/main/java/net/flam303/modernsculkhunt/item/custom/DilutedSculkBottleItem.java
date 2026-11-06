package net.flam303.modernsculkhunt.item.custom;

import net.flam303.modernsculkhunt.registries.ModAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DilutedSculkBottleItem extends HoneyBottleItem {

    public DilutedSculkBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {

        if (!level.isClientSide) {
            if (entityLiving instanceof Player player) {

                boolean isSculkified = player.getData(ModAttachments.IS_SCULKIFIED);

                if (!isSculkified) {

                    entityLiving.setData(ModAttachments.IS_SCULKIFIED, true);

                }

            }
        }

        return super.finishUsingItem(stack, level, entityLiving);
    }
}
