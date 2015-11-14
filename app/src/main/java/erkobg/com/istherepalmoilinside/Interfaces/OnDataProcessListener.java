package erkobg.com.istherepalmoilinside.Interfaces;


import com.parse.ParseException;

import erkobg.com.istherepalmoilinside.Entities.Product;

public interface OnDataProcessListener {
    void onProductCheckCompletedSuccess(Product oldProduct);

    void onProductCheckCompletedFail(String barcode);

    void onDataCheckCancel();

    void onDataSubmittedSuccess();

    void onDataSubmittedError(ParseException e);

    void onUserCreated();

    void onUserLogged();

}
