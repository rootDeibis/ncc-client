package me.rootdeibis.sixnine.client.mixin;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SixNineTweaker implements ITweaker {
    private List<String> launchArgs = new ArrayList<>();

    public static boolean hasOptifine = false;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {

        try {
            Class.forName("optifine.OptiFineTweaker");
            hasOptifine = true;
        } catch (ClassNotFoundException var6) {
        }

        if (!hasOptifine) {
            this.launchArgs.addAll(args);
            if (!args.contains("--version") && profile != null) {
                this.launchArgs.add("--version");
                this.launchArgs.add(profile);
            }

            if (assetsDir != null) {
                this.launchArgs.add("--assetsDir");
                this.launchArgs.add(assetsDir.getAbsolutePath());
            }

            if (gameDir != null) {
                this.launchArgs.add("--gameDir");
                this.launchArgs.add(gameDir.getAbsolutePath());
            }
        } else {
            this.launchArgs = new ArrayList();
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {

        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.sixnine.json");

        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }

        env.setSide(MixinEnvironment.Side.CLIENT);



        this.unlockLwjgl();
    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[0]);
    }

    @SuppressWarnings("unchecked")
    private void unlockLwjgl() {
        try {
            Field transformerExceptions = LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
            transformerExceptions.setAccessible(true);
            Object o = transformerExceptions.get(Launch.classLoader);
            ((Set<String>) o).remove("org.lwjgl.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
