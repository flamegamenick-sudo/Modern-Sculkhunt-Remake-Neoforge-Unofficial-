package net.flam303.modernsculkhunt.event;

import net.flam303.modernsculkhunt.registries.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = "modernsculkhunt", value = Dist.CLIENT/**, bus = EventBusSubscriber.Bus.GAME **/)
public class ClientEvents {

    @SubscribeEvent
    public static void onRightClick(InputEvent.InteractionKeyMappingTriggered event) {

        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.level == null || client.screen != null) {
            return;
        }

        Player player = client.player;
        Level level = client.level;

        if (event.isUseItem()) {

            double reach = 512d;

            if (player.hasData(ModAttachments.IS_SCULKIFIED) && player.getData(ModAttachments.IS_SCULKIFIED) && player.getMainHandItem().isEmpty()) {
                System.out.println("sculk right click without item");

                Vec3 startVec = player.getEyePosition();
                Vec3 lookVec = player.getViewVector(1.0f);
                Vec3 endVec = startVec.add(lookVec.scale(reach));

                BlockHitResult blockHit = level.clip(new ClipContext(
                        startVec,
                        endVec,
                        ClipContext.Block.OUTLINE,
                        ClipContext.Fluid.NONE,
                        player
                ));

                BlockPos pos = blockHit.getBlockPos();

                BlockState blockState = level.getBlockState(pos);

                System.out.println(blockState);

            }

        }

    }

}
