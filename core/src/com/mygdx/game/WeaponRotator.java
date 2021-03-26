package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class WeaponRotator {
    Sprite sprite;
    Vector2 v1, v2;
    float nR, tan;

    public WeaponRotator(Sprite sprite) {
        this.sprite = sprite;
        v1 = new Vector2();
        v2 = new Vector2();
    }

    void rtt(float spriteX, float spriteY, float shipX, float shipY, float granL, float granR){

        v1.set(0, -200);
        v2.set(spriteX - shipX, spriteY - shipY);
        tan = v1.angle(v2);

        if(tan > 0){
            if(Math.abs(tan) - 180 < nR) {
                if (nR > granL) {
                    nR -= 0.5;
                    sprite.rotate((float) -0.5);
                }
            }
            else {
                if (nR <= 0) {
                    nR += 0.5;
                    sprite.rotate((float) 0.5);
                }
            }
        }
        else {
            if(180 - Math.abs(tan) > nR) {
                if (nR < granR) {
                    nR += 0.5;
                    sprite.rotate((float) 0.5);
                }
            }
            else{
                if (nR > 0) {
                    nR -= 0.5;
                    sprite.rotate((float) -0.5);
                }
            }
        }
    }
}
