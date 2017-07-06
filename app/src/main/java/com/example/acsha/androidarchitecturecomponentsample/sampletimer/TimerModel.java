package com.example.acsha.androidarchitecturecomponentsample.sampletimer;

import lombok.Getter;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class TimerModel {

    @Getter
    private final long timerValue;

    public TimerModel(long timerValue) {
        this.timerValue = timerValue;
    }

}
