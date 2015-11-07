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

public class ShowProductFragment extends MyBaseFragment {


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        final String barCodeStr = args.getString("barCode");
        final String nameStr = args.getString("name");
        final String descrStr = args.getString("description");
        final Boolean hasPalmOilBoolean = args.getBoolean("hasPalmOil");


//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.show_product_fragment_layout, container, false);

        final TextView barcodeText = (TextView) v.findViewById(R.id.barcode);
        barcodeText.setText(barCodeStr);

        final TextView nameTxt = (TextView) v.findViewById(R.id.name);
        nameTxt.setText(nameStr);
        final TextView description = (TextView) v.findViewById(R.id.desc);
        description.setText(descrStr);
        final CheckBox HasPalmOil = (CheckBox) v.findViewById(R.id.HasPalmOil);
        if (hasPalmOilBoolean) {
            HasPalmOil.setChecked(true);
        }


        return v;
    }

}
