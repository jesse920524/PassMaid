package dev.jessefu.component_base.util;

import android.support.annotation.NonNull;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public final class TextDrawableUtil {

    private TextDrawableUtil(){
        throw new IllegalStateException(" should not be reached");
    }

    /**generate a textDrawable with given text
     * @param text
     *
     * @return */
    public static TextDrawable generate(@NonNull String text){

        char[] chars = text.toCharArray();
        String firstLetter = String.valueOf(chars[0]).toUpperCase();

        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
        TextDrawable textDrawable = TextDrawable.builder()
                .buildRound(firstLetter, colorGenerator.getRandomColor());
        return textDrawable;
    }
}
