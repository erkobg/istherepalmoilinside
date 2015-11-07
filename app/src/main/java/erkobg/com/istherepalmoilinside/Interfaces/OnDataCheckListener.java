package erkobg.com.istherepalmoilinside.Interfaces;

import com.firebase.client.DataSnapshot;

/**
 * Created by Erkobg on 11/07/2015.
 */
public interface OnDataCheckListener {
    void onProductCheckCompletedSuccess(DataSnapshot dataSnapshot);

    void onProductCheckCompletedFail();

    void onDataCheckCancel();


}
