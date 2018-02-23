package com.sz.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.sz.application.IApplication;

/**
 * http://blog.csdn.net/guolin_blog/article/details/53759439
 * http://www.jianshu.com/p/89567c934008
 * Created by E on 2017/12/6.
 */
public class GlideHelper {

    public static void display(ImageView imageView , String url){
        Glide.with(IApplication.getContext()).load(url)
                .into(imageView);
    }

    public static void display(ImageView imageView , int url){
        Glide.with(IApplication.getContext()).load(url)
                .into(imageView);
    }


    public static void display(Context context ,ImageView imageView , int url){
        Glide.with(context).load(url).into(imageView);
    }

    public static void display(ImageView imageView , String url , int corner){
        Glide.with(IApplication.getContext()).load(url)
                .bitmapTransform(new GlideRoundTransform(IApplication.getContext() , corner))
                .into(imageView);
    }

/*    public static void displayCircle(ImageView imageView , String url){
        Glide.with(IApplication.getContext()).load(url)
                .animate(R.anim.share_dialog_enter)
                .bitmapTransform(new GlideCircleTransform(IApplication.getContext()))
                .into(imageView);
    }*/

}
