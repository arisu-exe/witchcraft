package io.github.fallOut015.witchcraft.world.level.block;

import io.github.fallOut015.witchcraft.MainWitchcraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksWitchcraft {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MainWitchcraft.MODID);



    public static final RegistryObject<Block> TURQUOISE = BLOCKS.register("turquoise", () -> new TurquoiseBlock(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS, MaterialColor.CLAY).emissiveRendering(CrystalBlock::ifActivated).lightLevel(CrystalBlock::getLight).strength(2.0f).noOcclusion()));



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}