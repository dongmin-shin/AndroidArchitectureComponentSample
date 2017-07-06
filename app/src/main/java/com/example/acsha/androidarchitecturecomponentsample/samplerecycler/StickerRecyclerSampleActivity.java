package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerSampleActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI Binding
        setContentView(R.layout.activity_sticker_recycler_sample);

        // Bind Fragment
        StickerRecyclerFragment fragment = new StickerRecyclerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, StickerRecyclerFragment.TAG).commit();

    }
}
