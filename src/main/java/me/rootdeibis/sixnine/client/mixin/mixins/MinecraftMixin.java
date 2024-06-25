package me.rootdeibis.sixnine.client.mixin.mixins;

import me.rootdeibis.nc.event.events.TickEvent;
import me.rootdeibis.sixnine.client.NCClient;
import me.rootdeibis.sixnine.client.gui.GuiSplashScreen;

import me.rootdeibis.sixnine.client.gui.Notification;
import me.rootdeibis.sixnine.client.gui.main.GuiMainScreen;
import me.rootdeibis.sixnine.utils.font.FontUtils;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;

import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.nio.ByteBuffer;


@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    private DefaultResourcePack mcDefaultResourcePack;
    @Shadow
    abstract ByteBuffer readImageToBuffer(InputStream inputStream);

    @Shadow
    public abstract void displayGuiScreen(GuiScreen guiScreenIn);

    @Overwrite
    public void drawSplashScreen(TextureManager textureManagerInstance) {
        GuiSplashScreen.onRender(textureManagerInstance);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/SkinManager;<init>(Lnet/minecraft/client/renderer/texture/TextureManager;Ljava/io/File;Lcom/mojang/authlib/minecraft/MinecraftSessionService;)V"))
    public void progress1(CallbackInfo ci) {
        GuiSplashScreen.setProgress(1);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/storage/AnvilSaveConverter;<init>(Ljava/io/File;)V"))
    public void progress2(CallbackInfo ci) {
        GuiSplashScreen.setProgress(2);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/audio/SoundHandler;<init>(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/settings/GameSettings;)V"))
    public void progress3(CallbackInfo ci) {
        GuiSplashScreen.setProgress(3);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/audio/MusicTicker;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void progress4(CallbackInfo ci) {
        GuiSplashScreen.setProgress(4);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;<init>(Lnet/minecraft/client/settings/GameSettings;Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/client/renderer/texture/TextureManager;Z)V"))
    public void progress5(CallbackInfo ci) {
        GuiSplashScreen.setProgress(5);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/MouseHelper;<init>()V"))
    public void progress6(CallbackInfo ci) {
        GuiSplashScreen.setProgress(6);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureMap;<init>(Ljava/lang/String;)V"))
    public void progress7(CallbackInfo ci) {
        GuiSplashScreen.setProgress(7);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelManager;<init>(Lnet/minecraft/client/renderer/texture/TextureMap;)V"))
    public void progress8(CallbackInfo ci) {
        GuiSplashScreen.setProgress(8);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderItem;<init>(Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/resources/model/ModelManager;)V"))
    public void progress9(CallbackInfo ci) {
        GuiSplashScreen.setProgress(9);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;<init>(Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/renderer/entity/RenderItem;)V"))
    public void progress10(CallbackInfo ci) {
        GuiSplashScreen.setProgress(10);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void progress11(CallbackInfo ci) {
        GuiSplashScreen.setProgress(11);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;<init>(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/resources/IResourceManager;)V"))
    public void progress12(CallbackInfo ci) {
        GuiSplashScreen.setProgress(12);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BlockRendererDispatcher;<init>(Lnet/minecraft/client/renderer/BlockModelShapes;Lnet/minecraft/client/settings/GameSettings;)V"))
    public void progress13(CallbackInfo ci) {
        GuiSplashScreen.setProgress(13);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void progress14(CallbackInfo ci) {
        GuiSplashScreen.setProgress(14);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/achievement/GuiAchievement;<init>(Lnet/minecraft/client/Minecraft;)V"))
    public void progress15(CallbackInfo ci) {
        GuiSplashScreen.setProgress(15);
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    public void progress16(CallbackInfo ci) {
        GuiSplashScreen.setProgress(16);
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    public void replaceGuiIngame(CallbackInfo ci) {
        GuiSplashScreen.setProgress(17);
        NCClient.run();
    }



    @Redirect(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public void overrideDisplayTitle(String title) {
        Display.setTitle("NCC" + "v0.1"  + " for " + title);

    }


    @Inject(method = "displayGuiScreen", at = @At("RETURN"))
    public void displayGuiScreenInject(GuiScreen guiScreenIn, CallbackInfo ci) {
        if(guiScreenIn instanceof GuiMainMenu) {
            displayGuiScreen(new GuiMainScreen());
        }
    }

    @Inject(method = "runTick", at = @At("TAIL"))
    public void onTick(CallbackInfo info) {
        TickEvent event = new TickEvent();

        event.call();
    }


    /**
     * @author rootDeibis
     * @reason Custom client
     */
    @Overwrite
    private void setWindowIcon(){
        InputStream inputstream = null;
        InputStream inputstream1 = null;

        try
        {
            inputstream = this.getClass().getResourceAsStream("/assets/minecraft/sixnine/32.png");
            inputstream1 = this.getClass().getResourceAsStream("/assets/minecraft/sixnine/32.png");

            if (inputstream != null && inputstream1 != null)
            {
                Display.setIcon(new ByteBuffer[] {this.readImageToBuffer(inputstream), this.readImageToBuffer(inputstream1)});
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            IOUtils.closeQuietly(inputstream);
            IOUtils.closeQuietly(inputstream1);
        }


    }
}
