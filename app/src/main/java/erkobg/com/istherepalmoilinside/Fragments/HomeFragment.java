package erkobg.com.istherepalmoilinside.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import erkobg.com.istherepalmoilinside.R;

public class HomeFragment  extends Fragment  {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.home_layout, container, false);
        TextView mainText = (TextView) v.findViewById(R.id.mainText);
        mainText.setText(Html.fromHtml(getString(R.string.main_text)));
        return v;
    }

}