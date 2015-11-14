package erkobg.com.istherepalmoilinside.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.MainActivity;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class LoginUserFragment extends MyBaseFragment implements View.OnClickListener {
    private EditText mUsernameField;
    private EditText mPasswordField;
    private TextView mErrorField;
    private OnDataProcessListener listener;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.login_fragment, container, false);


        mUsernameField = (EditText) v.findViewById(R.id.register_username);
        mPasswordField = (EditText) v.findViewById(R.id.register_password);
        mErrorField = (TextView) v.findViewById(R.id.error_messages);

        listener = (MainActivity) getActivity();
        //assign listener for the Button
        final Button button = (Button) v.findViewById(R.id.sign_in);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        final View view = v;
        if (mUsernameField.getText().length() == 0 || mPasswordField.getText().length() == 0)
            return;

        //view.setEnabled(false);
        ParseUser.logInInBackground(mUsernameField.getText().toString(), mPasswordField.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            listener.onUserLogged();
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            switch (e.getCode()) {
                                case ParseException.USERNAME_TAKEN:
                                    mErrorField.setText(R.string.username_taken);
                                    break;
                                case ParseException.USERNAME_MISSING:
                                    mErrorField.setText(R.string.supply_username);
                                    break;
                                case ParseException.PASSWORD_MISSING:
                                    mErrorField.setText(R.string.supply_password);
                                    break;
                                case ParseException.OBJECT_NOT_FOUND:
                                    mErrorField.setText(R.string.credential_invalid);
                                    break;
                                default:
                                    mErrorField.setText(e.getLocalizedMessage());
                                    break;
                            }
                            //   view.setEnabled(true);
                        }
                    }
                }
        );

    }
}
