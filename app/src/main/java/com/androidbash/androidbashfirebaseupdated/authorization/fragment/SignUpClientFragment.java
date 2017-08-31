package com.androidbash.androidbashfirebaseupdated.authorization.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.User_client;
import com.androidbash.androidbashfirebaseupdated.authorization.activity.AuthorizationActivity;

import static android.content.Context.MODE_PRIVATE;

public class SignUpClientFragment extends BaseSignUpFragment {
    private View root;

    SharedPreferences isUserAdmin;

    private EditText name;
    private EditText phoneNumber;
    private EditText emailEt;
    private EditText passwordEt;
    private Button signUpBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Activity:","Start SignUpClientFragment");
        root = inflater.inflate(R.layout.activity_sign_up_client, container, false);

        initView();
        initListeners();

       isUserAdmin = this.getActivity().getSharedPreferences("isAdmin", MODE_PRIVATE);
        SharedPreferences.Editor editor = isUserAdmin.edit();
        editor.putBoolean("isAdmin", false);
//        editor.commit();
        editor.apply();

        return root;
    }
    private void initView() {
        signUpBtn = (Button) root.findViewById(R.id.button_user_sign_up);
        name = (EditText) root.findViewById(R.id.edit_text_username);
        phoneNumber = (EditText) root.findViewById(R.id.edit_text_phone_number);
        emailEt = (EditText) root.findViewById(R.id.edit_text_new_email);
        passwordEt = (EditText) root.findViewById(R.id.edit_text_new_password);

    }
    private void initListeners() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputData();
            }
        });
    }
    private void validateInputData() {
        name.setError(null);
        emailEt.setError(null);
        passwordEt.setError(null);
        phoneNumber.setError(null);

        String login = name.getText().toString();
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String confirmPassword = phoneNumber.getText().toString();

        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getActivity(), "Please enter name!", Toast.LENGTH_SHORT).show();
            name.setError("Please enter name!");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getActivity(), "Please enter mobile number!", Toast.LENGTH_SHORT).show();
            phoneNumber.setError( "Please enter mobile number");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email!", Toast.LENGTH_SHORT).show();
            emailEt.setError("Please enter email!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password!", Toast.LENGTH_SHORT).show();
            passwordEt.setError("Please enter password!");
            return;
        }




        ((AuthorizationActivity)getActivity()).createNewAccount(email, password, login,confirmPassword);

    }
}
