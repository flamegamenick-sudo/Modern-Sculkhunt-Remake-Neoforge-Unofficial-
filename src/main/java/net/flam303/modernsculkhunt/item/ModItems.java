package net.flam303.modernsculkhunt.item;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.flam303.modernsculkhunt.item.custom.DilutedSculkBottleItem;
import net.flam303.modernsculkhunt.item.custom.SculkBottleItem;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModernSculkhunt.MODID);

    public static final DeferredItem<Item> SCULK_BOTTLE = ITEMS.register("sculk_bottle", () -> new SculkBottleItem(new Item.Properties()));

    public static final DeferredItem<Item> SCULK_EYE = ITEMS.register("sculk_eye", () -> new EnderEyeItem(new Item.Properties()));

    public static final DeferredItem<Item> DILUTED_SCULK_BOTTLE = ITEMS.register("diluted_sculk_bottle", () -> new DilutedSculkBottleItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
