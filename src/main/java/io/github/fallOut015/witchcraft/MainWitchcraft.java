package io.github.fallOut015.witchcraft;

import io.github.fallOut015.witchcraft.world.level.block.BlocksWitchcraft;
import io.github.fallOut015.witchcraft.client.registry.BlockEntityRenderersWitchcraft;
import io.github.fallOut015.witchcraft.client.renderer.ItemBlockRenderTypesWitchcraft;
import io.github.fallOut015.witchcraft.world.item.ItemsEntomology;
import io.github.fallOut015.witchcraft.world.level.block.entity.BlockEntitiesWitchcraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MainWitchcraft.MODID)
public class MainWitchcraft {
    public static final String MODID = "witchcraft";

    public MainWitchcraft() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        BlocksWitchcraft.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsEntomology.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockEntitiesWitchcraft.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypesWitchcraft.doClientStuff(event);
        BlockEntityRenderersWitchcraft.doClientStuff(event);
    }
}