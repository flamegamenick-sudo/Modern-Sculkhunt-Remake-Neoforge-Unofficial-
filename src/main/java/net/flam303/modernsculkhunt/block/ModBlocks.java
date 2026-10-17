package net.flam303.modernsculkhunt.block;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.block.custom.ActiveSculkCatalystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ModernSculkhunt.MODID);

    public static final DeferredBlock<Block> ACTIVE_SCULK_CATALYST_BLOCK = BLOCKS.registerBlock(
            "active_sculk_catalyst_block",
            ActiveSculkCatalystBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SCULK_CATALYST)
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
