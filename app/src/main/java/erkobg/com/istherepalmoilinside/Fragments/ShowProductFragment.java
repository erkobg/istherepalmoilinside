package erkobg.com.istherepalmoilinside.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.CONSTANTS;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;
import erkobg.com.istherepalmoilinside.Utils.ResourcesAdditions;

public class ShowProductFragment extends MyBaseFragment {
    private Resources mResources;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();

        final String barCodeStr = args.getString(CONSTANTS.ARGUMENT_BAR_CODE);
        final String nameStr = args.getString(CONSTANTS.ARGUMENT_NAME);
        final String descrStr = args.getString(CONSTANTS.ARGUMENT_DESCRIPTION);
        final Boolean hasPalmOilBoolean = args.getBoolean(CONSTANTS.ARGUMENT_HAS_PALM_OIL);

/////////////////////////////////////////////////////
        mResources = this.getContext().getResources();
        Bundle bundle = ResourcesAdditions.getResourcesExtras(mResources, R.xml.res_xml_extras);

        //////////////////////////
        ///////////////////////////

//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.show_product_fragment_layout, container, false);

        final TextView barcodeText = (TextView) v.findViewById(R.id.barcode);
        barcodeText.setText(barCodeStr);


        final TextView originText = (TextView) v.findViewById(R.id.origin);
        if (barCodeStr.length() == 13) {
            String mcode = barCodeStr.substring(0, 3);
            String origin_country = bundle.getString(mcode);
            originText.setText(origin_country);
        } else {
            originText.setVisibility(View.GONE);
        }


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

        final Button linkButton = (Button) v.findViewById(R.id.button_url);
        linkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://www.digit-eyes.com/cgi-bin/digiteyes.fcgi?upcCode=" + barCodeStr + "&action=lookupUpc&go=Go%21");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });


        return v;
    }

}
