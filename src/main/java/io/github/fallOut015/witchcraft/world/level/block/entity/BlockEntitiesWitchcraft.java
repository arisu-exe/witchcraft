package io.github.fallOut015.witchcraft.world.level.block.entity;

import io.github.fallOut015.witchcraft.MainWitchcraft;
import io.github.fallOut015.witchcraft.world.level.block.BlocksWitchcraft;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesWitchcraft {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MainWitchcraft.MODID);



    public static final RegistryObject<BlockEntityType<CrystalBlockEntity>> CRYSTAL = BLOCK_ENTITIES.register("crystal", () -> BlockEntityType.Builder.of(CrystalBlockEntity::new, BlocksWitchcraft.TURQUOISE.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, "crystal")));



    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
