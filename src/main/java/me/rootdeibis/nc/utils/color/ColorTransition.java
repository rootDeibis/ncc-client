package me.rootdeibis.nc.utils.color;

import java.awt.*;

public class ColorTransition {

    private  long startTime;
    private  int speed;
    private  Color startColor;
    private  Color endColor;

    public boolean FINISHED = false;


    public ColorTransition(int transitionSpeed, Color initialColor, Color finalColor) {
        startTime = System.currentTimeMillis();
        speed = transitionSpeed;
        startColor = initialColor;
        endColor = finalColor;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
        FINISHED = false;
    }


    public Color getValue() {
        long currentTime = System.currentTimeMillis();
        float ratio = Math.min(1.0f, (float) (currentTime - startTime) / speed);

        int red = (int) (startColor.getRed() + ratio * (endColor.getRed() - startColor.getRed()));
        int green = (int) (startColor.getGreen() + ratio * (endColor.getGreen() - startColor.getGreen()));
        int blue = (int) (startColor.getBlue() + ratio * (endColor.getBlue() - startColor.getBlue()));

        if (ratio >= 1.0f) {
             FINISHED = true;
        }

        return new Color(red, green, blue);
    }
}