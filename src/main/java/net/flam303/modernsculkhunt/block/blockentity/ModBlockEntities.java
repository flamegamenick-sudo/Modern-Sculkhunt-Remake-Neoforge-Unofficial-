package net.flam303.modernsculkhunt.block.blockentity;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModernSculkhunt.MODID);

    public static final Supplier<BlockEntityType<ActiveSculkCatalystEntity>> ACTIVE_SCULK_CATALYST_BE =
            BLOCK_ENTITIES.register("active_sculk_catalyst_be", () -> BlockEntityType.Builder.of(
                    ActiveSculkCatalystEntity::new, ModBlocks.ACTIVE_SCULK_CATALYST_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
