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
import erkobg.com.istherepalmoilinside.Interfaces.OnDataCheckListener;

/**
 * Created by erkobg on 11/07/2015.
 */
public class FirebaseHelper {
    private AssetsPropertyReader assetsPropertyReader;
    private Properties properties;
    private static FirebaseHelper instance = null;
    String FirebaseDataURL = null;
    String ProductsStr = "Products";


    private OnDataCheckListener listener;

    private FirebaseHelper(Context context, OnDataCheckListener plistener) {
        // Exists only to defeat instantiation.
        if (context == null) {
            assetsPropertyReader = new AssetsPropertyReader(getContext());
        } else {
            assetsPropertyReader = new AssetsPropertyReader(context);
        }
        properties = assetsPropertyReader.getProperties("myproject.properties");
        FirebaseDataURL = properties.getProperty("MyFirebaseDataURL");
        listener = plistener;

    }

    public static FirebaseHelper getInstance(Context context, OnDataCheckListener plistener) {
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
            throw new Exception(" Properties not readed and URL is not valid");
        Firebase ref = new Firebase(FirebaseDataURL);
        Firebase prodRefCloud = ref.child(ProductsStr);
        prodRefCloud.push().setValue(new_product);
    }

    public void CheckProduct(String barcodeText) throws Exception {

        Log.d("FireBase", FirebaseDataURL);
        if (FirebaseDataURL == null || FirebaseDataURL.equals(""))
            throw new Exception(" Properties not readed and URL is not valid");
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
                    listener.onProductCheckCompletedFail();
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
