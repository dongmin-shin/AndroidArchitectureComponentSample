package utils;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author dong.min.shin on 2017. 7. 6..
 */

public class SimpleIdlingResource implements IdlingResource {

    // written from main thread, read from any thread.
    private volatile ResourceCallback mResourceCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    public void setIdleNow(boolean idleNow) {
        mIsIdleNow.set(idleNow);
        if (idleNow) {
            mResourceCallback.onTransitionToIdle();
        }
    }

    @Override
    public String getName() {
        return "Simple idling resource";
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public static void setIdlingResource(SimpleIdlingResource idlingRes) {
        Espresso.registerIdlingResources(idlingRes);
        idlingRes.setIdleNow(false);
    }

}
