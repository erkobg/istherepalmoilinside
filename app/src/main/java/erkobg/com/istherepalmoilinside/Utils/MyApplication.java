package erkobg.com.istherepalmoilinside.Utils;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.Properties;

import erkobg.com.istherepalmoilinside.Entities.Product;

public class MyApplication extends Application {
    private static Context appContext;

    public static Context getContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        ParseObject.registerSubclass(Product.class);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Exists only to defeat instantiation.
        AssetsPropertyReader assetsPropertyReader;
        assetsPropertyReader = new AssetsPropertyReader(this);
        Properties properties = assetsPropertyReader.getProperties("myproject.properties");

        Parse.initialize(this, properties.getProperty("applicationId"), properties.getProperty("clientKey"));

    }
}
