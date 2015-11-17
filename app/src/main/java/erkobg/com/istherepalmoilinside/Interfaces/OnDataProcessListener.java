package erkobg.com.istherepalmoilinside.Interfaces;


import erkobg.com.istherepalmoilinside.Entities.Product;

public interface OnDataProcessListener {
    void onProductCheckCompletedSuccess(Product product);

    void onProductSelected(Product product);

    void onProductCheckCompletedFail(String barcode, String barcodeformat);

    void onDataCheckCancel();

    void onDataSubmittedSuccess();

    void onDataSubmittedError(String error);

    void onUserCreated();

    void onUserLogged();

    void onUserLoggedOut();

}
