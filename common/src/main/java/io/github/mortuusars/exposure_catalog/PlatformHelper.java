package io.github.mortuusars.exposure_catalog;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

import java.util.function.Consumer;

public class PlatformHelper {
    public static void openMenu(ServerPlayer serverPlayer, MenuProvider menuProvider, Consumer<FriendlyByteBuf> extraDataWriter) {
        io.github.mortuusars.exposure_catalog.fabric.PlatformHelperImpl.openMenu(serverPlayer, menuProvider, extraDataWriter::accept);
    }

    public static boolean isModLoaded(String modId) {
        return io.github.mortuusars.exposure_catalog.fabric.PlatformHelperImpl.isModLoaded(modId);
    }

    public static boolean isInDevEnv() {
        return io.github.mortuusars.exposure_catalog.fabric.PlatformHelperImpl.isInDevEnv();
    }

    public static boolean checkCatalogCommandPermission(ServerPlayer player) {
        return io.github.mortuusars.exposure_catalog.fabric.PlatformHelperImpl.checkCatalogCommandPermission(player);
    }
}
