package net.flam303.modernsculkhunt.event;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.block.ModBlocks;
import net.flam303.modernsculkhunt.block.blockentity.ActiveSculkCatalystEntity;
import net.flam303.modernsculkhunt.block.blockentity.ModBlockEntities;
import net.flam303.modernsculkhunt.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = ModernSculkhunt.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onCatalystLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        if (!level.isClientSide && level.getBlockState(pos).is(ModBlocks.ACTIVE_SCULK_CATALYST_BLOCK.get())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ActiveSculkCatalystEntity catalyst) {
                Player player = event.getEntity();
                ItemStack heldItem = player.getMainHandItem();

                float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float chargeScale = player.getAttackStrengthScale(0.5f);

                float calculatedDamage = baseDamage * (0.2f + chargeScale * chargeScale * 0.8f);

                boolean isCrit = player.fallDistance > 0.0f
                        && !player.onGround()
                        && !player.onClimbable()
                        && !player.isInLiquid()
                        && !player.isPassenger();

                if (heldItem.getItem() instanceof MaceItem) {
                    if (player.fallDistance > 1.5F) {
                        float maceBonusPerBlock = 3.0F;
                        float extraMaceDamage = player.fallDistance * maceBonusPerBlock;

                        int densityLevel = heldItem.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.DENSITY));

                        int windLevel = heldItem.getEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.WIND_BURST));

                        if (windLevel > 0) {

                            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WIND_CHARGE_BURST, SoundSource.BLOCKS, 1.0f, 1.0f);

                            if (level instanceof ServerLevel serverLevel) {
                                serverLevel.sendParticles(ParticleTypes.GUST_EMITTER_LARGE.getType(), pos.getX() + 0.5D, pos.getY() + 1.2D, pos.getZ() + 0.5D, windLevel, 0.0D, 0.0D, 0.0D, 0.0D);
                            }

                            double verticalPush = 1.15D + (0.35D * windLevel);

                            player.setDeltaMovement(player.getDeltaMovement().x, verticalPush, player.getDeltaMovement().z);

                            player.hurtMarked = true;

                        }

                        if (densityLevel > 0) {
                            float densityBonus = (densityLevel * 0.5f) * player.fallDistance;
                            extraMaceDamage += extraMaceDamage;
                        }

                        calculatedDamage += extraMaceDamage;

                        player.fallDistance = 0.0F;
                    }
                }

                if (isCrit) {
                    calculatedDamage *= 1.5f;
                }

                ((ActiveSculkCatalystEntity) be).takeDamage(level, pos, calculatedDamage);

            }
        }
    }

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
