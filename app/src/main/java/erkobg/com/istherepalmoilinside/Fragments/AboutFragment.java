package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class AboutFragment extends MyBaseFragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.about_fragment_layout, container, false);
        TextView mainText = (TextView) v.findViewById(R.id.aboutText);
        mainText.setText(Html.fromHtml(getString(R.string.about_text)));
        return v;
    }


}
