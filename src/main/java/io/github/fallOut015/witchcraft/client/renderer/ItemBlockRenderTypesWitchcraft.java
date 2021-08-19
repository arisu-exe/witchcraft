package io.github.fallOut015.witchcraft.client.renderer;

import io.github.fallOut015.witchcraft.world.level.block.BlocksWitchcraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ItemBlockRenderTypesWitchcraft {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlocksWitchcraft.TURQUOISE.get(), RenderType.cutout());
    }
}