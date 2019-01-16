package com.wipro.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wipro.utils.StringUtils;

public class BindingAdapter {

    @android.databinding.BindingAdapter("handleText")
    public static void handleText(TextView tv, String s) {
        setVisibility(tv, s);
        tv.setText(s);
    }

    @android.databinding.BindingAdapter("handleImage")
    public static void handleImage(ImageView iv, String imgUrl) {
        setVisibility(iv, imgUrl);
        Glide.with(iv.getContext()).load(imgUrl).into(iv);
    }

    private static void setVisibility(View view, String s) {
        view.setVisibility(StringUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
    }
}
