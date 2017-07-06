package com.example.acsha.androidarchitecturecomponentsample.sampletimer;

import com.example.acsha.androidarchitecturecomponentsample.R;
import com.example.acsha.androidarchitecturecomponentsample.databinding.ActivityTimerSampleBinding;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class TimerSampleActivity extends LifecycleActivity {

    private ActivityTimerSampleBinding binding;
    private TimerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer_sample);

        // Get ViewModel
        viewModel = ViewModelProviders.of(this).get(TimerViewModel.class);

        // UI 변경 사항 구독
        subscribeUi();

        // ViewModel이 제공해주는 타이머 시작 API 호출
        viewModel.startTimer();
    }

    private void subscribeUi() {
        // ViewModel 갖고 있는 TimerModel에 대한 데이터 변경 사항을 관찰한다.
        viewModel.getTimerModelLiveData().observe(this, new Observer<TimerModel>() {
            @Override
            public void onChanged(@Nullable TimerModel timerModel) {
                binding.setTimer(timerModel);
            }
        });

    }
}
