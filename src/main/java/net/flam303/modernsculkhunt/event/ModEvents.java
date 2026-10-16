package net.flam303.modernsculkhunt.event;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = ModernSculkhunt.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack stack = event.getEntity().getItemInHand(hand);
        BlockPos pos = event.getPos();

        if (level.getBlockState(pos).is(Blocks.SCULK) && stack.is(Items.GLASS_BOTTLE)) {

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);

            if (!level.isClientSide) {
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);

                if (!player.isCreative()) {
                    stack.shrink(1);
                }

                ItemStack newItem = new ItemStack(ModItems.SCULK_BOTTLE.get());

                boolean success = player.getInventory().add(newItem);

                if (!success) {
                    player.drop(newItem, false);
                }

            }

        }
    }

}
