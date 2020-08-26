package net.nodium.mcmods.titlerename.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Shadow
    ServerInfo currentServerEntry;

    @Inject(method = "Lnet/minecraft/client/MinecraftClient;getWindowTitle()Ljava/lang/String;", at = @At(value = "HEAD"), cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        if (currentServerEntry != null) {
            cir.setReturnValue(currentServerEntry.name);
            cir.cancel();
        }
    }
}
