package erkobg.com.istherepalmoilinside.Utils;

import android.app.Application;
import android.content.Context;

class MyApplication extends Application {
    private static Context appContext;

    public static Context getContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
