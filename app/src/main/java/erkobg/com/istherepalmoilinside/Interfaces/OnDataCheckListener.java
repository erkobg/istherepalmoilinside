package erkobg.com.istherepalmoilinside.Interfaces;

import com.firebase.client.DataSnapshot;

import erkobg.com.istherepalmoilinside.Entities.Product;

/**
 * Created by Erkobg on 11/07/2015.
 */
public interface OnDataCheckListener {
    void onProductCheckCompletedSuccess(Product product);

    void onProductCheckCompletedFail();

    void onDataCheckCancel();


}
