package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class AboutFragment extends MyBaseFragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.about_layout, container, false);
    }


}
