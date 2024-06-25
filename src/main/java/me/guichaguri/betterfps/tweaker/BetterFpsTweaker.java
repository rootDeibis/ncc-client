package me.guichaguri.betterfps.tweaker;

import me.guichaguri.betterfps.BetterFpsHelper;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Chaguri
 */
public class BetterFpsTweaker implements ITweaker {

    private static final String[] TRANSFORMERS = new String[]{
            "me.guichaguri.betterfps.transformers.MathTransformer",
            "me.guichaguri.betterfps.transformers.EventTransformer",
            "me.guichaguri.betterfps.transformers.MiscTransformer",
            "me.guichaguri.betterfps.transformers.cloner.ClonerTransformer",
            "me.guichaguri.betterfps.transformers.VisualChunkTransformer",
            "me.guichaguri.betterfps.transformers.CapTransformer"
    };

    private final String[] EXCLUDED = new String[]{
            "me.guichaguri.betterfps.transformers",
            "me.guichaguri.betterfps.tweaker"
    };

    private List<String> launchArgs = new ArrayList<>();

    private boolean hasOptifine = false;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        try {
            Class.forName("optifine.OptiFineTweaker");
            hasOptifine = true;
        } catch (ClassNotFoundException var6) {
        }



        BetterFpsHelper.MCDIR = gameDir;
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader cl) {

        for(String transformer : TRANSFORMERS) {
            cl.registerTransformer(transformer);
        }

        for(String excluded : EXCLUDED) {
            cl.addTransformerExclusion(excluded);
        }

    }

    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {

        ArrayList args = (ArrayList)Launch.blackboard.get("ArgumentList");
        if(args.isEmpty()) args.addAll(this.launchArgs);


        return new String[0];
    }
}
