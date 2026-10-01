package io.github.mortuusars.exposure_catalog.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public record ExposureThumbnail(int width, int height, byte[] pixels, Identifier paletteId) {
    public static final StreamCodec<ByteBuf, ExposureThumbnail> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ExposureThumbnail::width,
            ByteBufCodecs.VAR_INT, ExposureThumbnail::height,
            ByteBufCodecs.BYTE_ARRAY, ExposureThumbnail::pixels,
            Identifier.STREAM_CODEC, ExposureThumbnail::paletteId,
            ExposureThumbnail::new
    );
}
