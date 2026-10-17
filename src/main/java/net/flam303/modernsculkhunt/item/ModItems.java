package net.flam303.modernsculkhunt.item;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.block.ModBlocks;
import net.flam303.modernsculkhunt.item.custom.SculkBottleItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModernSculkhunt.MODID);

    public static final DeferredItem<Item> SCULK_BOTTLE = ITEMS.register("sculk_bottle", () -> new SculkBottleItem(new Item.Properties()));

    public static final DeferredItem<BlockItem> ACTIVE_SCULK_CATALYST = ITEMS.registerSimpleBlockItem(
            "active_sculk_catalyst",
            ModBlocks.ACTIVE_SCULK_CATALYST_BLOCK,
            new Item.Properties()
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
