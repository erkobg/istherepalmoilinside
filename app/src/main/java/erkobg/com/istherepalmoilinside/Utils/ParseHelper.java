package erkobg.com.istherepalmoilinside.Utils;

import android.content.Context;

import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;


public class ParseHelper {
    private final static String ProductsStr = Product.productClassName;
    private static ParseHelper instance = null;
    private final OnDataProcessListener listener;

    private ParseHelper(Context context, OnDataProcessListener plistener) {

        listener = plistener;

    }

    @SuppressWarnings("unchecked")
    public static ParseHelper getInstance(Context context, OnDataProcessListener plistener) {
        if (instance == null) {
            instance = new ParseHelper(context, plistener);
        }
        return instance;
    }

    // [Optional] Call this whenever you want the app context.
    private Context getContext() {
        return MyApplication.getContext();
    }

    public void CreateNewProduct(Product new_product) throws Exception {


        // 1
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        new_product.setACL(acl);

        //2 Add the user who is creating the record
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseRelation relation = new_product.getRelation(Product.usernameColumn);
        relation.add(currentUser);

        // 2
        new_product.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    listener.onDataSubmittedSuccess();
                } else {

                    listener.onDataSubmittedError(e.getMessage());
                }

            }
        });

    }

    public void CheckProduct(String barcodeText, String barcodeFormat) throws Exception {
        final String barcode = barcodeText;
        final String barcodeformat = barcodeFormat;
        // 1
        ParseQuery<Product> mapQuery = Product.getQuery();
        // 2
        mapQuery.whereEqualTo(Product.barcodeColumn, barcodeText);

        // 3
        mapQuery.getFirstInBackground(new GetCallback<Product>() {
            @Override
            public void done(Product object, ParseException e) {
                if (e == null) {
                    listener.onProductCheckCompletedSuccess(object);
                } else {
                    listener.onProductCheckCompletedFail(barcode, barcodeformat);
                }
            }


        });


    }
}
