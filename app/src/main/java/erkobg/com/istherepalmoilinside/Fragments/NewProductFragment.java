package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import erkobg.com.istherepalmoilinside.Entities.Product;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.CONSTANTS;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;
import erkobg.com.istherepalmoilinside.Utils.ParseHelper;

public class NewProductFragment extends MyBaseFragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        final String barCode = args.getString(CONSTANTS.ARGUMENT_BAR_CODE);
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
                //First validate inputs
                String l_barcodeText = barcodeText.getText().toString().trim();
                String l_name = name.getText().toString().trim();
                String l_description = description.getText().toString().trim();
                if (l_barcodeText.length() == 0) {
                    barcodeText.setError(getString(R.string.mandat_barcode));
                    return;
                } else {
                    barcodeText.setError(null);
                }
                if (l_name.length() == 0) {
                    name.setError(getString(R.string.mandat_name));
                    return;
                } else {
                    name.setError(null);
                }
                if (l_description.length() == 0) {
                    description.setError(getString(R.string.mandat_description));
                    return;
                } else {
                    description.setError(null);
                }


                //second if everything is ok - add the OldProduct
                try {
                    ParseHelper tmp = ParseHelper.getInstance(null, null);
                    Product new_product = new Product();
                    new_product.setBarcode(l_barcodeText);
                    new_product.setName(l_name);
                    new_product.setDescription(l_description);
                    new_product.setHasPalmOil(HasPalmOil.isChecked());
                    tmp.CreateNewProduct(new_product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }


}
