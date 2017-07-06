package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.databinding.StickerItemBinding;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.image.ImageInfo;

import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerAdapter extends RecyclerView.Adapter<StickerRecyclerAdapter.StickerRecyclerViewHolder> {

    enum Animation {
        PLAY, STOP;
    }

    List<Sticker> originStickerList = new ArrayList<>();

    private final StickerClickCallback stickerClickCallback;

    public StickerRecyclerAdapter(StickerClickCallback stickerClickCallback) {
        this.stickerClickCallback = stickerClickCallback;
    }

    public void setStickerList(final List<Sticker> newStickerList) {
        if (newStickerList == null) {
            this.originStickerList = newStickerList;
            notifyItemRangeInserted(0, newStickerList.size());

        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return originStickerList.size();
                }

                @Override
                public int getNewListSize() {
                    return newStickerList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    // Sticker의 Id를 이용해 같은 아이템인지 여부를 비교
                    return originStickerList.get(oldItemPosition).getId() == newStickerList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Sticker newSticker = newStickerList.get(newItemPosition);
                    Sticker oldSticker = originStickerList.get(oldItemPosition);

                    return newSticker.getId() == oldSticker.getId()
                            && (newSticker.getImageUrl().equals(oldSticker.getImageUrl()));
                }
            });

            originStickerList = newStickerList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public StickerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StickerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sticker_item, parent, false);
        binding.setCallback(stickerClickCallback);

        return new StickerRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StickerRecyclerViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(StickerRecyclerViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        Sticker sticker = originStickerList.get(position);
        holder.binding.setSticker(sticker);

        // Set Controller
        DraweeController draweeController;
        if (!payloads.isEmpty() && payloads.get(0).equals(Animation.PLAY)) {
            draweeController = getAnimateDraweeController(sticker);
        } else {
            draweeController = getNormalDraweeController(sticker);
        }

        holder.binding.stickerImage.setController(draweeController);

        // Binding 된 View를 업데이트한다.
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return originStickerList == null ? 0 : originStickerList.size();
    }

    public void playAnimation(Sticker sticker) {
        Log.d("TEST", "[playAnimation] " + sticker.toString());
        notifyItemChanged(sticker.getId(), Animation.PLAY);
    }

    static class StickerRecyclerViewHolder extends RecyclerView.ViewHolder {

        final StickerItemBinding binding;

        public StickerRecyclerViewHolder(StickerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private DraweeController getNormalDraweeController(Sticker sticker) {
        return Fresco.newDraweeControllerBuilder()
                .setUri(sticker.getImageUrl())
                .build();
    }

    private DraweeController getAnimateDraweeController(Sticker sticker) {
        return Fresco.newDraweeControllerBuilder()
                .setUri(sticker.getImageUrl())
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
