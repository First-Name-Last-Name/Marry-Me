package com.example;

import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public interface MarriageProperties {
    void marry_me$tpToPartner();
    void marry_me$partnerProximityEffects();
    void marry_me$tpToMarriageHome();


    boolean marry_me$getIsMarried();
    UUID marry_me$getPartnerID();
    BlockPos marry_me$getMarriageHome();

    void marry_me$setIsMarried(boolean isMarried);
    void marry_me$setPartnerID(UUID partnerUUID);
    void marry_me$setMarriageHome(BlockPos marriageHome);
}
