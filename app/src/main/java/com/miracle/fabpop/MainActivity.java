package com.miracle.fabpop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miracle
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TextView tv;
    private RvAdapter adapter;
    private List<String> data = new ArrayList<>();

    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean isVisible = true;
    private manager ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ins = manager.getInstance();
        ins.showFragment(this);

        rv = findViewById(R.id.rv);
        tv = findViewById(R.id.tv);
        initData();
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // newState的状态变化
                // 0——>停滞
                // 1——>拖拽
                // 2——>滚动
                Log.d("滚动状态", "onScrollStateChanged: "+newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 可视item的下标增大的滑动，dy>0（上滑）
                // 可视item的下标减小的滑动，dy<0（下滑）
                // 上滑和下滑是相对于手势来看
                Log.d("滚动距离变化量", "onScrolled: dy="+dy);
                if (scrolledDistance > HIDE_THRESHOLD && isVisible) {
                    // 上滑和下滑是相对于手势来看
                    // 上滑隐藏
                    hide();
                    ins.hide();
                    isVisible = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !isVisible) {
                    // 下滑显示
                    show();
                    ins.show();
                    isVisible = true;
                    scrolledDistance = 0;
                }

                if((isVisible && dy>0) || (!isVisible && dy<0)) {
                    scrolledDistance += dy;
                }
            }
        });



    }

    private void show() {
        float currentTranslationX = tv.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"translationX",currentTranslationX,0);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void hide() {
        // 其实就是0
        float currentTranslationX = tv.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"translationX",currentTranslationX,util.dp2px(this,40));
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            data.add("item"+i);
        }
        adapter = new RvAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

}
