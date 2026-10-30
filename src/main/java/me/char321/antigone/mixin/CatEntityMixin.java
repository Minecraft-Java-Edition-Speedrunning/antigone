package me.char321.antigone.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.*;
import net.minecraft.world.gen.StructureAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin {
    @ModifyExpressionValue(
            method = "initialize",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getStructureAccessor()Lnet/minecraft/world/gen/StructureAccessor;")
    )
    private StructureAccessor wrapOffThreadGetChunk(StructureAccessor original, @Local(argsOnly = true) WorldAccess world) {
        return original.forRegion((ChunkRegion) world);
    }
}
