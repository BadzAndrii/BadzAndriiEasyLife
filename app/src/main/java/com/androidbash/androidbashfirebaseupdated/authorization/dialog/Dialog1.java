package com.androidbash.androidbashfirebaseupdated.authorization.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.authorization.activity.AuthorizationActivity;

//// TODO: 01.09.17 Що за назва? 
public class Dialog1 extends DialogFragment implements View.OnClickListener {

  final String LOG_TAG = "myLogs";

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    getDialog().setTitle("Зареєструватись як:");
    View v = inflater.inflate(R.layout.dialog1, null);
    v.findViewById(R.id.btnYes).setOnClickListener(this);
    v.findViewById(R.id.btnNo).setOnClickListener(this);

    return v;
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnYes:
        //                Intent intent_client = new Intent(getActivity(), SignUpActivityClient.class);
        //                startActivity(intent_client);
        ((AuthorizationActivity) getActivity()).showSignUpClient();
        break;
      case R.id.btnNo:
        //                Intent intent_admin = new Intent(getActivity(), SignUpActivityAdmin.class);
        //                startActivity(intent_admin);
        ((AuthorizationActivity) getActivity()).showSignUpAdmin();
        break;
      default:
        break;
    }
    dismiss();
  }

  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
  }

  public void onCancel(DialogInterface dialog) {
    super.onCancel(dialog);
  }
  public void onResume(){
    super.onResume();
    Window window = getDialog().getWindow();
    window.setLayout(450, 350);
    window.setGravity(Gravity.CENTER);
  }
}