package com.example.kalpesh.tailornew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.kalpesh.tailornew.Api.ApiCall;

public class signupActivity extends AppCompatActivity {
    EditText et_name,et_address,et_password,et_cno,et_email;
    RadioGroup rg_sexgroup;
    RadioButton rb_sexgroup;
    Button btn_login;
    String name,address,password, gender,cno,email;

    private SharedPreferences sharedPreferences;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_name=(EditText)findViewById(R.id.et_name);
        et_address=(EditText)findViewById(R.id.et_address);
        et_password=(EditText)findViewById(R.id.et_password);
        et_cno=(EditText)findViewById(R.id.et_cno);
        et_email=(EditText)findViewById(R.id.et_email);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.et_address, "^(?=\\s*\\S).*$", R.string.addresserror);
        awesomeValidation.addValidation(this, R.id.et_cno, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);



        btn_login=(Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=et_name.getText().toString().trim();
                password=et_password.getText().toString().trim();
                address=et_address.getText().toString().trim();
                rg_sexgroup=(RadioGroup)findViewById(R.id.rg_sexgroup);
                cno=et_cno.getText().toString().trim();
                email=et_email.getText().toString().trim();

                if (awesomeValidation.validate()) {
                    int i=rg_sexgroup.getCheckedRadioButtonId();
                    rb_sexgroup=(RadioButton)findViewById(i);
                    gender=rb_sexgroup.getText().toString().trim();
                    //   Toast.makeText(getApplicationContext(),gender,Toast.LENGTH_LONG).show();
                    new AddUSer().execute();

                }

            }
        });
    }
    public class AddUSer extends AsyncTask<Void,Void,String>{
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(signupActivity.this);
            dialog.setTitle("Please Wait..!");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api=new ApiCall();
            String result=api.AddUser(name, gender,address,email,password,cno);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Added...!")){
                sharedPreferences=getSharedPreferences(LoginActivity.MyPREFRENCES,0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(LoginActivity.USERNAME,email);
                editor.putString(LoginActivity.PASSWORD,password);
                editor.putString(LoginActivity.GENDER,gender);
                editor.commit();

                startActivity(new Intent(signupActivity.this,MainActivity.class));
                finish();
            }else {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        }
    }
}
