package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.viewmodel;

import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.UriUtils;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerListViewModel extends ViewModel {

    private static final int MAX_STICKER_COUNT = 8;

    public final MutableLiveData<List<Sticker>> stickerList = new MutableLiveData<>();

    public void initLoad(Context context) {
        List<Sticker> newStickerList = new ArrayList<>();

        for (int i = 0; i < MAX_STICKER_COUNT; i++) {
            int stickerResId = context.getResources().getIdentifier("line_sample_" + (i + 1), "drawable", context.getPackageName());
            Uri stickerUri = UriUtils.toUri(context.getResources(), stickerResId);

            Sticker sticker = new Sticker(i, stickerUri.toString());
            newStickerList.add(sticker);
        }

        stickerList.setValue(newStickerList);
    }

    public MutableLiveData<List<Sticker>> getStickerList() {
        return stickerList;
    }
}
