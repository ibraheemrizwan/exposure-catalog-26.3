package io.github.mortuusars.exposure_catalog.fabric;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.serialization.MapCodec;
import io.github.mortuusars.exposure_catalog.ExposureCatalog;
import io.github.mortuusars.exposure_catalog.Register;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.advancements.predicates.entity.EntitySubPredicate;
import net.minecraft.core.component.predicates.DataComponentPredicate;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.Codec;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegisterImpl {
    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.BLOCK, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> blockEntityType(String id, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends BlockEntity> BlockEntityType<T> newBlockEntityType(Register.BlockEntitySupplier<T> blockEntitySupplier, Block... validBlocks) {
        return net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.create(blockEntitySupplier::create, validBlocks).build();
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.ITEM, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends CreativeModeTab> Supplier<T> creativeTab(String id, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends Entity> Supplier<EntityType<T>> entityType(String id, EntityType.EntityFactory<T> factory,
                                                                        MobCategory category, float width, float height,
                                                                        int clientTrackingRange, boolean velocityUpdates, int updateInterval) {
        EntityType<T> type = Registry.register(BuiltInRegistries.ENTITY_TYPE, ExposureCatalog.resource(id),
                EntityType.Builder.of(factory, category)
                        .sized(width, height)
                        .clientTrackingRange(clientTrackingRange)
                        .alwaysUpdateVelocity(velocityUpdates)
                        .updateInterval(updateInterval)
                        .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ExposureCatalog.resource(id))));
        return () -> type;
    }

    public static <T extends Entity> Supplier<EntityType<T>> entityType(String id, EntityType.EntityFactory<T> factory, MobCategory category, boolean receiveVelocityUpdates, Consumer<EntityType.Builder<T>> typeBuilder) {
        EntityType.Builder<T> builder = EntityType.Builder.of(factory, category);
        typeBuilder.accept(builder);
        builder.alwaysUpdateVelocity(receiveVelocityUpdates);
        EntityType<T> type = Registry.register(BuiltInRegistries.ENTITY_TYPE, ExposureCatalog.resource(id), builder.build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ExposureCatalog.resource(id))));
        return () -> type;
    }

    public static <T extends SoundEvent> Supplier<T> soundEvent(String id, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.SOUND_EVENT, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends MenuType<E>, E extends AbstractContainerMenu> Supplier<MenuType<E>> menuType(String id, Register.MenuTypeSupplier<E> supplier) {
        ExtendedMenuType<E, byte[]> type = new ExtendedMenuType<>((syncId, inventory, data) -> {
            RegistryFriendlyByteBuf buffer = new RegistryFriendlyByteBuf(Unpooled.wrappedBuffer(data), inventory.player.registryAccess());
            E menu = supplier.create(syncId, inventory, buffer);
            buffer.release();
            return menu;
        }, ByteBufCodecs.BYTE_ARRAY.mapStream(Function.identity()));

        Registry.register(BuiltInRegistries.MENU, ExposureCatalog.resource(id), type);

        return () -> {
            return type;
        };
    }

    public static Supplier<RecipeType<?>> recipeType(String id, Supplier<RecipeType<?>> supplier) {
        RecipeType<?> obj = Registry.register(BuiltInRegistries.RECIPE_TYPE, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends net.minecraft.world.item.crafting.Recipe<?>> Supplier<RecipeSerializer<T>> recipeSerializer(String id, Supplier<RecipeSerializer<T>> supplier) {
        RecipeSerializer<T> obj = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ExposureCatalog.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends CriterionTrigger<?>> Supplier<T> criterionTrigger(String name, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.TRIGGER_TYPES, ExposureCatalog.resource(name), supplier.get());
        return () -> obj;
    }

    public static <T extends DataComponentPredicate.Type<?>> Supplier<T> itemSubPredicate(String name, Supplier<T> supplier) {
        T obj = Registry.register(BuiltInRegistries.DATA_COMPONENT_PREDICATE_TYPE, ExposureCatalog.resource(name), supplier.get());
        return () -> obj;
    }

    public static <T extends EntitySubPredicate> Supplier<Codec<T>> entitySubPredicate(String name, Supplier<Codec<T>> supplier) {
        Codec<T> obj = Registry.register(BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE, ExposureCatalog.resource(name), supplier.get());
        return () -> obj;
    }

    public static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>, I extends ArgumentTypeInfo<A, T>>
    Supplier<ArgumentTypeInfo<A, T>> commandArgumentType(String id, Class<A> infoClass, I argumentTypeInfo) {
        ArgumentTypeRegistry.registerArgumentType(ExposureCatalog.resource(id), infoClass, argumentTypeInfo);
        return () -> argumentTypeInfo;
    }

    public static <T extends Feature> Supplier<MapCodec<T>> worldGenFeature(String name, Supplier<MapCodec<T>> featureSupplier) {
        MapCodec<T> feature = Registry.register(BuiltInRegistries.FEATURE_TYPE, name, featureSupplier.get());
        return () -> feature;
    }

    public static <T> DataComponentType<T> dataComponentType(String name, Consumer<DataComponentType.Builder<T>> builderConsumer) {
        var builder = DataComponentType.<T>builder();
        builderConsumer.accept(builder);
        var componentType = builder.build();
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ExposureCatalog.resource(name), componentType);
    }

    public static <T extends ParticleType<? extends ParticleOptions>> Supplier<T> particleType(String name, Supplier<T> supplier) {
        T particleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, name, supplier.get());
        return () -> particleType;
    }
}

