package com.androidbash.androidbashfirebaseupdated.authorization.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.authorization.activity.AuthorizationActivity;

public class SignInFragment extends Fragment{

    private View root;
    private Button signInBtn;
    private Button signUpBtn;
    private EditText emailEt;
    private EditText passwordEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Activity:","Start SignInFragment");
        root = inflater.inflate(R.layout.fragment_signin, container, false);

        initView();
        initListeners();

        return root;
    }


    private void initView() {
        signInBtn = (Button) root.findViewById(R.id.button_sign_in);
        signUpBtn = (Button) root.findViewById(R.id.button_sign_up);
        emailEt = (EditText) root.findViewById(R.id.edit_text_email_id);
        passwordEt = (EditText) root.findViewById(R.id.edit_text_password);

    }

    private void initListeners() {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AuthorizationActivity) getActivity()).onShowRegistrationDialog();
            }
        });
    }


    private void validateForm() {

        emailEt.setError(null);
        passwordEt.setError(null);
        String login = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getActivity(), "Будь ласка, введіть логін!", Toast.LENGTH_SHORT).show();
            emailEt.setError("Будь ласка, введіть логін!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Будь ласка, введіть пароль!", Toast.LENGTH_SHORT).show();
            passwordEt.setError("Будь ласка, введіть пароль!");
            return;
        }

        ((AuthorizationActivity) getActivity()).signIn(login, password);

    }
    //test code
    private void validateData() {

        //все добре із даними

        //надіслати запит у Activity
//        ((AuthorizationActivity)getActivity()).signIn("email", "password");
    }


}
