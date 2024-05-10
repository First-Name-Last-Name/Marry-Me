package com.example.mixin;


import com.example.MarriageProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements MarriageProperties {
    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void tickPartnerProximityEffects(CallbackInfo ci) {
        this.marry_me$partnerProximityEffects();
    }



    @Unique private boolean isMarried;
    @Unique private UUID partnerUUID;
    @Unique private BlockPos marriageHome;





    @Override
    public void marry_me$tpToPartner() {
        if (this.isMarried) {
            if (this.partnerUUID != null) {
                Entity t = (Entity) (Object) this;
                Entity e = t.getEntityWorld().getPlayerByUuid(this.partnerUUID);
                if (e != null) {
                    t.setPos(e.getX(), e.getY(), e.getZ());
                }
            }
        }
    }

    @Override
    public void marry_me$partnerProximityEffects() {
        if (this.isMarried) {
            if (this.partnerUUID != null) {
                LivingEntity t = (LivingEntity) (Object) this;
                Entity e = t.getEntityWorld().getPlayerByUuid(this.partnerUUID);


                if (e != null) {
                    if (Math.sqrt(Math.pow(t.getX() - e.getX(), 2) + Math.pow(t.getY() - e.getY(), 2) + Math.pow(t.getY() - e.getY(), 2)) < 10) {
                        if (t.getStatusEffect(StatusEffects.REGENERATION) == null) {
                            t.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0, true, false, true));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void marry_me$tpToMarriageHome() {
        if (this.isMarried) {
            if (this.marriageHome != null) {
                ((Entity) (Object) this).setPos(this.marriageHome.getX(), this.marriageHome.getY(), this.marriageHome.getZ());
            }
        }
    }





    @Override
    public boolean marry_me$getIsMarried() {
        return isMarried;
    }
    @Override
    public UUID marry_me$getPartnerID() {
        return partnerUUID;
    }
    @Override
    public BlockPos marry_me$getMarriageHome() {
        return marriageHome;
    }

    @Override
    public void marry_me$setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }
    @Override
    public void marry_me$setPartnerID(UUID partnerUUID) {
        this.partnerUUID = partnerUUID;
    }
    @Override
    public void marry_me$setMarriageHome(BlockPos marriageHome) {
        this.marriageHome = marriageHome;
    }
}