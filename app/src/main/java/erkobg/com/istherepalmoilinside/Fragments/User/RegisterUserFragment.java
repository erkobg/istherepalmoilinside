package erkobg.com.istherepalmoilinside.Fragments.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import erkobg.com.istherepalmoilinside.Interfaces.OnDataProcessListener;
import erkobg.com.istherepalmoilinside.MainActivity;
import erkobg.com.istherepalmoilinside.R;
import erkobg.com.istherepalmoilinside.Utils.MyBaseFragment;

public class RegisterUserFragment extends MyBaseFragment implements View.OnClickListener {
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mEmailField;
    private TextView mErrorField;
    private OnDataProcessListener listener;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


//Inflate the layout for this fragment
        View v = inflater.inflate(
                R.layout.register_user_fragment, container, false);


        mUsernameField = (EditText) v.findViewById(R.id.register_username);
        mPasswordField = (EditText) v.findViewById(R.id.register_password);
        mEmailField = (EditText) v.findViewById(R.id.register_email);
        mErrorField = (TextView) v.findViewById(R.id.error_messages);

        listener = (MainActivity) getActivity();
        //assign listener for the Button
        final Button button = (Button) v.findViewById(R.id.sign_up);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        final View view = v;
        if (mUsernameField.length() == 0) {
            mUsernameField.setError(getString(R.string.user_mandatory));
            return;
        } else {
            mUsernameField.setError(null);
        }
        if (mPasswordField.length() == 0) {
            mPasswordField.setError(getString(R.string.pass_mandatory));
            return;
        } else {
            mPasswordField.setError(null);
        }
        if (mEmailField.length() == 0) {
            mEmailField.setError(getString(R.string.email_mandatory));
            return;
        } else {
            mEmailField.setError(null);
        }


        view.setEnabled(false);
        ParseUser user = new ParseUser();
        user.setUsername(mUsernameField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());
        user.setEmail(mEmailField.getText().toString());


        mErrorField.setText("");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    listener.onUserCreated();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
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
                        case ParseException.EMAIL_MISSING:
                            mErrorField.setText(R.string.email_missing);
                            break;
                        case ParseException.EMAIL_TAKEN:
                            mErrorField.setText(R.string.email_taken);
                            break;
                        case ParseException.INVALID_EMAIL_ADDRESS:
                            mErrorField.setText(R.string.invalid_email);
                            break;
                        default:
                            mErrorField.setText(e.getLocalizedMessage());
                    }
                    view.setEnabled(true);
                }
            }
        });
    }

}
