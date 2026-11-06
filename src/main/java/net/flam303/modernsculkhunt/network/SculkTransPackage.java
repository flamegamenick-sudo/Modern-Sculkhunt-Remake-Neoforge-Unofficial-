package net.flam303.modernsculkhunt.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SculkTransPackage(BlockPos targetPos) implements CustomPacketPayload {

    public static final Type<SculkTransPackage> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath("modernsculkhunt", "sculk_trans")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SculkTransPackage> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            SculkTransPackage::targetPos,
            SculkTransPackage::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
