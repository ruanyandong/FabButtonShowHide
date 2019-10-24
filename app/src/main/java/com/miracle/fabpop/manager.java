package com.miracle.fabpop;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author miracle
 * @date 2019-10-24
 * @email ruanyandongai@gmail.com
 * @blog https://ruanyandong.github.io
 */
public class manager {
    private static final manager instance = new manager();

    public static manager getInstance() {
        return instance;
    }

    private AdFragment fragment;

    public void showFragment(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragment = new AdFragment();

        if (fragmentManager != null && fragment.isAdded()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        }
        fragment.show(fragmentManager, "111");

    }

    public void show() {
        if (fragment != null) {
            fragment.show();
        }
    }

    public void hide() {
        if (fragment != null) {
            fragment.hide();
        }
    }

}
