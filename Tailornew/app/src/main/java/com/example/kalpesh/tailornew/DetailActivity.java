package com.example.kalpesh.tailornew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalpesh.tailornew.Api.ApiCall;
import com.example.kalpesh.tailornew.model.AllUser;
import com.example.kalpesh.tailornew.model.user;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ImageView imgpic1;
    TextView tv_itemname,tv_rate;
    Button btn_book;
    private SharedPreferences sharedPreferences;
    String userid,name,addnote,itemid;
    private ProgressDialog dialog;
    EditText et_addnote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgpic1=(ImageView)findViewById(R.id.imgpic1);

        tv_itemname=(TextView)findViewById(R.id.tv_itemname);
        tv_rate=(TextView)findViewById(R.id.tv_rate);

        et_addnote=(EditText)findViewById(R.id.et_addnote);

        btn_book=(Button)findViewById(R.id.btn_book);

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFRENCES, 0);
        name=sharedPreferences.getString(LoginActivity.USERNAME,"");

        Intent in=getIntent();
        Bundle b=in.getExtras();
        if (b!=null){

            String itemname=b.getString("itemname");

            String rate=b.getString("rate");
           // tv_rate.setText(rate);
            String temp="Item "+itemname+" for "+rate+"/-";
            tv_itemname.setText(temp);
            itemid=b.getString("itemid");
            String uri=b.getString("uri");
            Picasso.get()
                    .load(uri)
                    .fit()
                    .error(R.drawable.login)
                    .into(imgpic1);

        }
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addnote=et_addnote.getText().toString().trim();

                new Getuserid().execute();

            }
        });
    }
    public class Getuserid extends AsyncTask<Void,Void,AllUser>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= new ProgressDialog(DetailActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
        }

        @Override
        protected AllUser doInBackground(Void... voids) {
            ApiCall api=new ApiCall();
            AllUser result=api.GetUserid(name);

            return result;
        }

        @Override
        protected void onPostExecute(AllUser allUser) {
            super.onPostExecute(allUser);
            if (allUser!=null){
                for (user u:allUser.getData()){
                    userid=u.getUserid();
                 //   Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_LONG).show();
                   new Assigntask().execute();
                }
            }
            dialog.dismiss();
        }
    }
    public class Assigntask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api=new ApiCall();
            String result=api.Addtask(userid,addnote,itemid);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Added...!")){
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                finish();
            }else {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailActivity.this,MainActivity.class));
        super.onBackPressed();
    }
}
