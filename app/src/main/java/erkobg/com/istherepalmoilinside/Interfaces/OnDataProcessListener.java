package erkobg.com.istherepalmoilinside.Interfaces;

import erkobg.com.istherepalmoilinside.Entities.Product;

public interface OnDataProcessListener {
    void onProductCheckCompletedSuccess(Product oldProduct);

    void onProductCheckCompletedFail(String barcode);

    void onDataCheckCancel();

    void onDataSubmitted();

    void onUserCreated();

    void onUserLogged();

}
