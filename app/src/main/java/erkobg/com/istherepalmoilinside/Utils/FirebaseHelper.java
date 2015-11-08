package erkobg.com.istherepalmoilinside.Utils;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.Properties;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.R;

/**
 * Created by erkobg on 11/07/2015.
 */
public class FirebaseHelper {
    private static FirebaseHelper instance = null;
    private String FirebaseDataURL = null;
    private final static String ProductsStr = "Products";


    private final OnDataProcessListener listener;

    private FirebaseHelper(Context context, OnDataProcessListener plistener) {
        // Exists only to defeat instantiation.
        AssetsPropertyReader assetsPropertyReader;
        if (context == null) {
            assetsPropertyReader = new AssetsPropertyReader(getContext());
        } else {
            assetsPropertyReader = new AssetsPropertyReader(context);
        }
        Properties properties = assetsPropertyReader.getProperties("myproject.properties");
        FirebaseDataURL = properties.getProperty("MyFirebaseDataURL");
        listener = plistener;

    }

    public static FirebaseHelper getInstance(Context context, OnDataProcessListener plistener) {
        if (instance == null) {
            instance = new FirebaseHelper(context, plistener);
        }
        return instance;
    }

    // [Optional] Call this whenever you want the app context.
    private Context getContext() {
        return MyApplication.getContext();
    }

    public void CreateNewProduct(Product new_product) throws Exception {

        Log.d("FireBase", FirebaseDataURL);
        if (FirebaseDataURL == null || FirebaseDataURL.equals(""))
            throw new Exception(getContext().getString(R.string.firebase_url_empty));

        Firebase ref = new Firebase(FirebaseDataURL);
        Firebase prodRefCloud = ref.child(ProductsStr);
        prodRefCloud.push().setValue(new_product);
        listener.onDataSubmitted();
    }

    public void CheckProduct(String barcodeText) throws Exception {
        final String barcode = barcodeText;
        Log.d("FireBase", FirebaseDataURL);
        if (FirebaseDataURL == null || FirebaseDataURL.equals(""))
            throw new Exception(getContext().getString(R.string.firebase_url_empty));
        Firebase productRef = new Firebase(FirebaseDataURL);
        Log.d("CheckProduct", barcodeText);

        Query queryRef = productRef.child(ProductsStr).orderByChild("barcode").equalTo(barcodeText);

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    System.out.println(snapshot.getKey());
                    Log.v("FirebaseHelper", "Exists");
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Product product = postSnapshot.getValue(Product.class);
                        listener.onProductCheckCompletedSuccess(product);

                    }

                } else {
                    Log.v("FirebaseHelper", "Not Existing");
                    listener.onProductCheckCompletedFail(barcode);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("FirebaseHelper", "onCancelled");
                listener.onDataCheckCancel();
            }
        });

    }
}
