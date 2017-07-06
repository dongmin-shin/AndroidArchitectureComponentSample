package com.example.acsha.androidarchitecturecomponentsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class TimerViewModel extends AndroidViewModel {

    private static final int ONE_SECOND = 1000;

    private final MutableLiveData<TimerModel> timerModelLiveData = new MutableLiveData<>();

    public TimerViewModel(Application application) {
        super(application);
    }

    public LiveData<TimerModel> getTimerModelLiveData() {
        return timerModelLiveData;
    }

    // 실제 1초마다 값을 변경하는 로직은 ViewModel에 존재해야 한다.
    public void startTimer() {
        Log.d("TEST", "[startTimer]");

        final long initialTime = SystemClock.elapsedRealtime();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - initialTime) / 1000;

                /* MainThread가 아닌 곳에서 LiveData.setValue()를 호출하게 되면
                java.lang.IllegalStateException: Cannot invoke setValue on a background thread 이 발생한다.
                 */
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        timerModelLiveData.setValue(new TimerModel(newValue));
                    }
                });
            }

        }, ONE_SECOND, ONE_SECOND);
    }
}
