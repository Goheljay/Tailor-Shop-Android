package com.example.kalpesh.tailornew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.kalpesh.tailornew.Api.ApiCall;

public class LoginActivity extends AppCompatActivity {
    EditText et_email, et_password;
    Button btn_login, btn_signup;
    String uname, password;

    public static final String MyPREFRENCES = "myprefs";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String GENDER = "gender";
    private SharedPreferences sharedPreferences;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFRENCES, 0);
        uname = sharedPreferences.getString(LoginActivity.USERNAME, "");
        password = sharedPreferences.getString(LoginActivity.PASSWORD, "");

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidation.setColor(Color.MAGENTA);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.et_password, "^(?=\\s*\\S).*$", R.string.passworderror);


        if (!uname.equals("") || !password.equals("")) {
            //Toast.makeText(getApplicationContext(), uname, Toast.LENGTH_LONG).show();
            new userLogin().execute();
        }

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, signupActivity.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();

                sharedPreferences = getSharedPreferences(LoginActivity.MyPREFRENCES, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(LoginActivity.USERNAME, uname);
                editor.putString(LoginActivity.PASSWORD, password);
                editor.commit();

                if (awesomeValidation.validate()) {
                    if (uname.equals("a@g.com") && password.equals("a")) {
                        startActivity(new Intent(LoginActivity.this, AdditemActivity.class));
                    } else {
                        new userLogin().execute();
                    }
                }
            }
        });
    }

    public class userLogin extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            String result = api.userLogin(uname, password);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Login Successful")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else if (s.equals("Invalid Login Details")) {
                new TailorLogin().execute();
            }
            dialog.dismiss();
        }
    }

    public class TailorLogin extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            String result = api.TailorLogin(uname, password);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Login Successful")) {
                startActivity(new Intent(LoginActivity.this, TailotSeeTaskActivity.class));
                finish();
            }
            dialog.dismiss();
        }
    }
}