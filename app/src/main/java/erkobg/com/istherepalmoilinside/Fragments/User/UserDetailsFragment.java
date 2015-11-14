package erkobg.com.istherepalmoilinside.Fragments.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class UserDetailsFragment extends MyBaseFragment {
    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mCreatedAtField;
    private TextView mErrorField;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.user_details_fragment, container, false);


        mUsernameField = (EditText) v.findViewById(R.id.register_username);
        mEmailField = (EditText) v.findViewById(R.id.register_email);
        mCreatedAtField = (EditText) v.findViewById(R.id.created_at);
        mErrorField = (TextView) v.findViewById(R.id.error_messages);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            mUsernameField.setText(currentUser.getUsername());
            mEmailField.setText(currentUser.getEmail());
            mCreatedAtField.setText(currentUser.getCreatedAt().toString());
        } else {
            // show the signup or login screen
        }

        return v;
    }


}
