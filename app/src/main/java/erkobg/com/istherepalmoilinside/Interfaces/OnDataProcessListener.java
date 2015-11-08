package erkobg.com.istherepalmoilinside.Interfaces;

import erkobg.com.istherepalmoilinside.Entities.Product;

/**
 * Created by Erkobg on 11/07/2015.
 */
public interface OnDataProcessListener {
    void onProductCheckCompletedSuccess(Product product);

    void onProductCheckCompletedFail(String barcode);

    void onDataCheckCancel();

    void onDataSubmitted();

}
