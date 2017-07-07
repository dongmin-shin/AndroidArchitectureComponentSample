package com.example.acsha.androidarchitecturecomponentsample.samplerecycler.model;

import android.net.Uri;

import lombok.Getter;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class Sticker {

    @Getter
    private final int id;

    @Getter
    private final Uri imageUri;

    public Sticker(int id, Uri imageUri) {
        this.id = id;
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return id + "/" + imageUri;
    }
}
