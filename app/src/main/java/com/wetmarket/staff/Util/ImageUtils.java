package com.wetmarket.staff.Util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.ViewPropertyTransition;


public class ImageUtils {

    public static void loadImage(Context context, String filePath, int placeholder, ImageView imageView) {
        if (!TextUtils.isEmpty(filePath)) {


            ViewPropertyTransition.Animator animationObject = new ViewPropertyTransition.Animator() {
                @Override
                public void animate(View view) {
                    view.setAlpha(0f);
                    ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                    fadeAnim.setDuration(500);
                    fadeAnim.start();
                }
            };

            Glide.with(context)
                    .load(filePath)
                    .thumbnail(0.1f)
                    .transition(GenericTransitionOptions.with(animationObject))
                    .apply(new RequestOptions().placeholder(placeholder).error(placeholder))
                    .into(imageView);
        } else {
            imageView.setImageResource(placeholder);
        }

    }

    public static void loadImageBlur(Context context, String filePath, int placeholder, ImageView imageView) {
       /* if (!TextUtils.isEmpty(filePath)) {
            Glide.with(context)
                    .load(filePath)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions().placeholder(placeholder).error(placeholder)).transform(new GlideBlurTransformation(context))
                    .into(imageView);
        } else {
            imageView.setImageResource(placeholder);
        }*/

    }

}
