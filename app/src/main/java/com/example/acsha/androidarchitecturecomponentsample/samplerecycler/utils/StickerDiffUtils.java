package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.utils;

import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 7..
 */

public class StickerDiffUtils {

    @NonNull
    public static DiffUtil.DiffResult getDiffResult(final List<Sticker> newStickerList, final List<Sticker> originStickerList) {
        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
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
    }

}
