package erkobg.com.istherepalmoilinside.Fragments.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.MainActivity;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class UserDetailsFragment extends MyBaseFragment {
    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mCreatedAtField;
    private Button bLogout;
    private OnDataProcessListener listener;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.user_details_fragment, container, false);


        mUsernameField = (EditText) v.findViewById(R.id.register_username);
        mEmailField = (EditText) v.findViewById(R.id.register_email);
        mCreatedAtField = (EditText) v.findViewById(R.id.created_at);

        bLogout = (Button) v.findViewById(R.id.button_log_out);

        listener = (MainActivity) getActivity();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            mUsernameField.setText(currentUser.getUsername());
            mEmailField.setText(currentUser.getEmail());
            mCreatedAtField.setText(currentUser.getCreatedAt().toString());
        } else {
            // show the signup or login screen
        }

        bLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {

                ParseUser.logOut();
                listener.onUserLoggedOut();

            }
        });

        return v;
    }


}
