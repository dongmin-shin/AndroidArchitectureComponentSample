package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.viewmodel;

import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.UriUtils;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerListViewModel extends AndroidViewModel {

    private static final int MAX_STICKER_COUNT = 14;

    public final MutableLiveData<List<Sticker>> stickerList = new MutableLiveData<>();

    public StickerRecyclerListViewModel(Application application) {
        super(application);

        initLoad();
    }

    public MutableLiveData<List<Sticker>> getStickerList() {
        return stickerList;
    }

    private void initLoad() {
        Application context = this.getApplication();

        List<Sticker> newStickerList = new ArrayList<>();
        for (int i = 0; i < MAX_STICKER_COUNT; i++) {
            int stickerResId = context.getResources().getIdentifier("line_sample_" + (i + 1), "drawable", context.getPackageName());
            Uri stickerUri = UriUtils.toUri(context.getResources(), stickerResId);

            Sticker sticker = new Sticker(i, stickerUri.toString());
            newStickerList.add(sticker);
        }

        stickerList.setValue(newStickerList);
    }

}
