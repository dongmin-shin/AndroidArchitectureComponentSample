package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.databinding.FragmentStickerRecyclerBinding;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model.Sticker;
import com.example.acsha.androidarchitecturecomponentsample.samplerecycler.viewmodel.StickerRecyclerListViewModel;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class StickerRecyclerFragment extends LifecycleFragment {

    public static final String TAG = StickerRecyclerFragment.class.getSimpleName();

    private FragmentStickerRecyclerBinding binding;
    private StickerRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sticker_recycler, container, false);

        adapter = new StickerRecyclerAdapter();
        binding.stickerList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StickerRecyclerListViewModel viewModel = ViewModelProviders.of(this).get(StickerRecyclerListViewModel.class);

        subscribeUi(viewModel);

        viewModel.initLoad(getActivity());
    }

    private void subscribeUi(StickerRecyclerListViewModel viewModel) {
        viewModel.getStickerList().observe(this, new Observer<List<Sticker>>() {
            @Override
            public void onChanged(@Nullable List<Sticker> stickers) {
                Log.d("TEST", "[onChanged] stickers: " + stickers);
                if (stickers != null) {
                    adapter.setStickerList(stickers);
                }
            }
        });
    }
}
