package net.flam303.modernsculkhunt.item;

import net.flam303.modernsculkhunt.ModernSculkhunt;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModernSculkhunt.MODID);

    public static void register(IEventBus eventBus) {CREATIVE_MODE_TAB.register(eventBus);}

    public static final Supplier<CreativeModeTab> SCULKHUNT_ITEMS = CREATIVE_MODE_TAB.register("sculkhunt_items", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SCULK_EYE.get())).title(Component.translatableEscape("creativetab.modernsculkhunt.sculkhunt_items"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.SCULK_BOTTLE);
                output.accept(ModItems.SCULK_EYE);
                output.accept(ModItems.DILUTED_SCULK_BOTTLE);
            })
            .build());

}
