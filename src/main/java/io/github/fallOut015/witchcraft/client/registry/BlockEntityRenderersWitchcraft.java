package io.github.fallOut015.witchcraft.client.registry;

import io.github.fallOut015.witchcraft.client.renderer.blockentity.CrystalRenderer;
import io.github.fallOut015.witchcraft.world.level.block.entity.BlockEntitiesWitchcraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class BlockEntityRenderersWitchcraft {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockEntitiesWitchcraft.CRYSTAL.get(), CrystalRenderer::new);
    }
}