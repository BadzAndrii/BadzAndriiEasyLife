package com.androidbash.androidbashfirebaseupdated.authorization.fragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
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

public class BaseSignUpFragment extends Fragment {

    protected boolean isAdminFlag = false;

    private View root;

    //SharedPreferences isUserAdmin;

    private EditText name;
    private EditText phoneNumber;
    private EditText emailEt;
    private EditText passwordEt;
    private Button signUpBtn;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        Log.v("Activity:", "Start SignUpClientFragment");
        root = inflater.inflate(R.layout.activity_sign_up_client, container, false);

        initView();
        initListeners();

        //isUserAdmin = this.getActivity().getSharedPreferences("isAdmin", MODE_PRIVATE);
        //SharedPreferences.Editor editor = isUserAdmin.edit();
        //editor.putBoolean("isAdmin", false);
        ////        editor.commit();
        //editor.apply();

        return root;
    }

    private void initView() {
        signUpBtn = (Button) root.findViewById(R.id.button_user_sign_up);
        name = (EditText) root.findViewById(R.id.edit_text_username);
        phoneNumber = (EditText) root.findViewById(R.id.edit_text_phone_number);
        emailEt = (EditText) root.findViewById(R.id.edit_text_new_email);
        passwordEt = (EditText) root.findViewById(R.id.edit_text_new_password);

        name.getBackground().setColorFilter(getResources().getColor(R.color.your_color), PorterDuff.Mode.SRC_ATOP);
        phoneNumber.getBackground().mutate().setColorFilter(getResources().getColor(R.color.your_color), PorterDuff.Mode.SRC_ATOP);
    }

    private void initListeners() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
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
            Toast.makeText(getActivity(), "Будь ласка, введіть ім'я!", Toast.LENGTH_SHORT).show();
            name.setError("Будь ласка, введіть ім'я");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getActivity(), "Будь ласка, введіть Ваш мобільний номер!", Toast.LENGTH_SHORT).show();
            phoneNumber.setError("Будь ласка, введіть мобільний номер!");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Будь ласка, введіть пошту!", Toast.LENGTH_SHORT).show();
            emailEt.setError("Будь ласка, введіть пошту!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Будь ласка, введіть пароль!", Toast.LENGTH_SHORT).show();
            passwordEt.setError("Будь ласка, введіть пароль");
            return;
        }

        ((AuthorizationActivity) getActivity()).createNewAccount(email, password, login,
            confirmPassword, isAdminFlag);
    }

}
