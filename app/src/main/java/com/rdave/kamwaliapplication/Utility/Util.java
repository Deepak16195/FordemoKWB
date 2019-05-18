package com.rdave.kamwaliapplication.Utility;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.Random;

/**
 * Created by user on 31-03-2018.
 */

public class Util {
    /*-----------------Olx Type Place Holder Diffrent Color Glide Loding------------------------*/
    private static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ebf1dd")),
                    new ColorDrawable(Color.parseColor("#e5e0ec")),
                    new ColorDrawable(Color.parseColor("#f2dcdb")),
                    new ColorDrawable(Color.parseColor("#fdeada")),

            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }
    /*------------------------------------------------------------------------------------------*/
}
