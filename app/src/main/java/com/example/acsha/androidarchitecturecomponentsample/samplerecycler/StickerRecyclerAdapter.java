package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.databinding.StickerItemBinding;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.utils.AnimationDraweeController;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.utils.StickerDiffUtils;
import com.facebook.drawee.interfaces.DraweeController;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerAdapter extends RecyclerView.Adapter<StickerRecyclerAdapter.StickerRecyclerViewHolder> {

    public enum Animation {
        PLAY
    }

    private final AnimationDraweeController animationDraweeController = new AnimationDraweeController();

    private List<Sticker> originStickerList = new ArrayList<>();

    public void setStickerList(final List<Sticker> newStickerList) {
        if (newStickerList == null) {
            this.originStickerList = newStickerList;
            notifyItemRangeInserted(0, newStickerList.size());

        } else {
            DiffUtil.DiffResult result = StickerDiffUtils.getDiffResult(newStickerList, originStickerList);

            originStickerList = newStickerList;
            result.dispatchUpdatesTo(this);
        }
    }


    @Override
    public StickerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StickerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sticker_item, parent, false);
        binding.setAdapter(this);

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
        DraweeController draweeController = animationDraweeController.getDraweeController(payloads, sticker);
        holder.binding.stickerImage.setController(draweeController);

        // Binding 된 View를 업데이트한다.
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return originStickerList == null ? 0 : originStickerList.size();
    }

    public void playAnimation(Sticker sticker) {
        notifyItemChanged(sticker.getId(), Animation.PLAY);
    }

    static class StickerRecyclerViewHolder extends RecyclerView.ViewHolder {

        final StickerItemBinding binding;

        public StickerRecyclerViewHolder(StickerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
