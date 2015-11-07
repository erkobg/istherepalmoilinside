package erkobg.com.istherepalmoilinside.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Field;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.FirebaseHelper;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class NewProductFragment extends MyBaseFragment {


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        final String barCode = args.getString("barCode");
//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.new_product_fragment_layout, container, false);

        final TextView barcodeText = (TextView) v.findViewById(R.id.barcode);
        barcodeText.setText(barCode);

        final TextView name = (TextView) v.findViewById(R.id.name);
        final TextView description = (TextView) v.findViewById(R.id.desc);
        final CheckBox HasPalmOil = (CheckBox) v.findViewById(R.id.HasPalmOil);

        //assign listener for the Button
        final Button button = (Button) v.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FirebaseHelper tmp = FirebaseHelper.getInstance(null, null);

                    tmp.CreateNewProduct(barcodeText.getText().toString(), name.getText().toString(), description.getText().toString(), HasPalmOil.isEnabled());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }


}
