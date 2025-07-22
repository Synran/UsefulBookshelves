package site.coderan.usefulbookshelf.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import site.coderan.usefulbookshelf.ModMain;

public record ConfigChangeData(int mode, String descriptionId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ConfigChangeData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(ModMain.MOD_ID, "config_change_data"));

    public static  final StreamCodec<ByteBuf, ConfigChangeData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ConfigChangeData::mode,
            ByteBufCodecs.STRING_UTF8,
            ConfigChangeData::descriptionId,
            ConfigChangeData::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
