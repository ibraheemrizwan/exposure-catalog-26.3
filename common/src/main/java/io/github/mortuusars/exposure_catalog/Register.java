package io.github.mortuusars.exposure_catalog;

import io.github.mortuusars.exposure_catalog.fabric.RegisterImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/** Fabric registration entry point; no bytecode platform transformation is required. */
public class Register extends RegisterImpl {
    @FunctionalInterface
    public interface BlockEntitySupplier<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }
    @FunctionalInterface
    public interface MenuTypeSupplier<T extends AbstractContainerMenu> {
        T create(int windowId, Inventory playerInv, RegistryFriendlyByteBuf extraData);
    }
}

