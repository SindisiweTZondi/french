package com.jesusm.fingerprintmanager.javasample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hkm.ui.processbutton.iml.ActionProcessButton;

import utils.ProgressGenerator;


public class SignInActivity extends Activity implements ProgressGenerator.OnCompleteListener {

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sign_in);

        final EditText editEmail = (EditText) findViewById(R.id.editSname);
        //final EditText editPassword = (EditText) findViewById(R.id.editPassword);

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final ActionProcessButton btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);


            btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);

        btnSignIn.setOnClickNormalState(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSignIn);
               // btnSignIn.setEnabled(false);
               // editEmail.setEnabled(false);
             //   editPassword.setEnabled(false);
            }
        }).build();;
    }

    @Override
    public void onComplete() {
       // Toast.makeText(this, R.string.Loading_Complete, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

}
