package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.databinding.StickerItemBinding;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;

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

    List<Sticker> originStickerList = new ArrayList<>();

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
        return new StickerRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StickerRecyclerViewHolder holder, int position) {
        Sticker sticker = originStickerList.get(position);
        holder.binding.setSticker(sticker);

        // Binding 된 View를 업데이트한다.
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return originStickerList == null ? 0 : originStickerList.size();
    }

    static class StickerRecyclerViewHolder extends RecyclerView.ViewHolder {

        final StickerItemBinding binding;

        public StickerRecyclerViewHolder(StickerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
