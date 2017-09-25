package com.androidbash.androidbashfirebaseupdated.authorization.fragment;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidbash.androidbashfirebaseupdated.R;
import com.androidbash.androidbashfirebaseupdated.authorization.activity.AuthorizationActivity;

public class SignUpAdminFragment extends BaseSignUpFragment {
  private View root;

  public SignUpAdminFragment() {
    this.isAdminFlag = true;
  }
}