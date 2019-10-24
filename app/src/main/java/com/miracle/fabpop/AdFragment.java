package com.miracle.fabpop;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @author miracle
 * @date 2019-10-24
 * @email ruanyandongai@gmail.com
 * @blog https://ruanyandong.github.io
 */
public class AdFragment extends AppCompatDialogFragment {
    private View view;
    private LinearLayout root;
    private ImageView iv_bg;
    private ImageView iv_close;

    private static final int DIALOG_WIDTH = 88;
    private static final int DIALOG_HEIGHT = 104;
    private static final int DIALOG_BOTTOM = 170;


    @Override
    public void onStart() {
        super.onStart();

        Window win = getDialog().getWindow();
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams params = win.getAttributes();
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        params.width = 176;
        params.height = 400;
        params.y = DIALOG_BOTTOM;
        win.setAttributes(params);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feedpopup, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        root = view.findViewById(R.id.root);
        iv_bg = view.findViewById(R.id.ad_icon);
        iv_close = view.findViewById(R.id.close_icon);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {
        float currentTranslationX = root.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(root, "translationX", currentTranslationX, 0);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    public void hide() {
        // 其实就是0
        float currentTranslationX = root.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(root, "translationX", currentTranslationX, util.dp2px(getContext(), 40));
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
