package com.miracle.fabpop;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author miracle
 * @date 2019-10-22
 * @email ruanyandongai@gmail.com
 * @blog https://ruanyandong.github.io
 */
public class util {

    public static int dp2px(Context context, float dpValue) {
        Resources r = context.getResources();
        float scale = (float)r.getDisplayMetrics().densityDpi;
        return (int)(dpValue * (scale / 160.0F) + 0.5F);
    }
    public static float px2dp(Context context, float pxValue) {
        Resources r = context.getResources();
        float scale = (float)r.getDisplayMetrics().densityDpi;
        return pxValue * 160.0F / scale;
    }
}
