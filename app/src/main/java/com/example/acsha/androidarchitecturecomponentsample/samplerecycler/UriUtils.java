package com.example.acsha.androidarchitecturecomponentsample.samplerecycler;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;

/**
 * @author dong.min.shin on 2017. 7. 3..
 */

public class UriUtils {

    public static Uri toUri(Resources resources, int resId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resId) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(resId));
    }
}
