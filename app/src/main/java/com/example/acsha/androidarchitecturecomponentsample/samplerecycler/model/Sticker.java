package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model;

import lombok.Getter;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class Sticker {

    @Getter
    private final int id;

    @Getter
    private final String imageUrl;

    public Sticker(int id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
