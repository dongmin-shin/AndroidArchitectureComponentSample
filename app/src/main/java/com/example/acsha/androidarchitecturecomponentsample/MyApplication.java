package com.example.acsha.androidarchitecturecomponentsample;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import android.app.Application;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, config);
    }
}
