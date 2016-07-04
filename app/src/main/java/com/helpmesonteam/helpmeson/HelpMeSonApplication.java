package com.helpmesonteam.helpmeson;

import android.app.Application;
import android.content.Context;

import com.android.framework.utils.Utils;
import com.helpmesonteam.helpmeson.manager.SHA256FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by karthikeyan on 04-07-2016.
 */
public class HelpMeSonApplication extends Application {


    private static HelpMeSonApplication instance;

    public static HelpMeSonApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader(getApplicationContext());
        Utils.globalConfigurationIntitalize(getApplicationContext());
    }


    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new SHA256FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
