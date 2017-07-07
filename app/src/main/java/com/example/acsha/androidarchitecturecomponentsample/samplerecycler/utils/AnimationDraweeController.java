package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.utils;

import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.StickerRecyclerAdapter;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class AnimationDraweeController {

    public DraweeController getDraweeController(List<Object> payloads, Sticker sticker) {
        DraweeController draweeController;
        if (!payloads.isEmpty() && payloads.get(0).equals(StickerRecyclerAdapter.Animation.PLAY)) {
            draweeController = getAnimateDraweeController(sticker);
        } else {
            draweeController = getNormalDraweeController(sticker);
        }
        return draweeController;
    }

    private DraweeController getNormalDraweeController(Sticker sticker) {

        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(createImageRequest(sticker))
                .setControllerListener(normalControllerListener)
                .build();
    }

    private ImageRequest createImageRequest(Sticker sticker) {
        return ImageRequestBuilder
                .newBuilderWithSource(sticker.getImageUri())
                .build();
    }

    @NonNull
    private final ControllerListener<ImageInfo> normalControllerListener = new ControllerListener<ImageInfo>() {
        @Override
        public void onSubmit(String id, Object callerContext) {
            Log.d("TEST", "[onSubmit]");
        }

        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            Log.d("TEST", "[onFinalImageSet]: " + id);
        }

        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            Log.d("TEST", "[onIntermediateImageSet]");
        }

        @Override
        public void onIntermediateImageFailed(String id, Throwable throwable) {
            Log.d("TEST", "[onIntermediateImageFailed]");
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
            Log.d("TEST", "[onFailure]");
        }

        @Override
        public void onRelease(String id) {
            Log.d("TEST", "[onRelease]");
        }
    };

    private DraweeController getAnimateDraweeController(Sticker sticker) {
        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(createImageRequest(sticker))
                .setControllerListener(new ControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {
                        Log.d("TEST", "[onSubmit]");
                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        Log.d("TEST", "[onFinalImageSet]");

                        if (animatable != null) {
                            AnimatedDrawable animatedDrawable = (AnimatedDrawable) animatable;
                            ValueAnimator valueAnimator = animatedDrawable.createValueAnimator();
                            valueAnimator.setRepeatCount(2);
                            valueAnimator.start();
                        }
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        Log.d("TEST", "[onIntermediateImageSet]");
                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {
                        Log.d("TEST", "[onIntermediateImageFailed]");
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        Log.d("TEST", "[onFailure]");
                    }

                    @Override
                    public void onRelease(String id) {
                        Log.d("TEST", "[onRelease]");
                    }
                })
                .build();

    }
}

