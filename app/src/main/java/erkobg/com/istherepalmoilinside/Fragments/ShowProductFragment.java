package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.CONSTANTS;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class ShowProductFragment extends MyBaseFragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        final String barCodeStr = args.getString(CONSTANTS.ARGUMENT_BAR_CODE);
        final String nameStr = args.getString(CONSTANTS.ARGUMENT_NAME);
        final String descrStr = args.getString(CONSTANTS.ARGUMENT_DESCRIPTION);
        final Boolean hasPalmOilBoolean = args.getBoolean(CONSTANTS.ARGUMENT_HAS_PALM_OIL);


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
        final ImageView imageView = (ImageView) v.findViewById(R.id.image_HasPalmOil);

        if (hasPalmOilBoolean) {
            HasPalmOil.setChecked(true);
            imageView.setImageResource(R.drawable.yes_big);
        } else {
            imageView.setImageResource(R.drawable.no_big);

        }


        return v;
    }

}
