package io.github.fallOut015.witchcraft.world.item;

import io.github.fallOut015.witchcraft.MainWitchcraft;
import io.github.fallOut015.witchcraft.world.level.block.BlocksWitchcraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsEntomology {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainWitchcraft.MODID);



    public static final RegistryObject<Item> TURQUOISE = ITEMS.register("turquoise", () -> new BlockItem(BlocksWitchcraft.TURQUOISE.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}