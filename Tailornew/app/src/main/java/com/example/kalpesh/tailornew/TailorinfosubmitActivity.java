package com.example.kalpesh.tailornew;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalpesh.tailornew.Api.ApiCall;
import com.example.kalpesh.tailornew.SmsActivity;
import com.example.kalpesh.tailornew.model.AllStaff;
import com.example.kalpesh.tailornew.model.AllTtask;
import com.example.kalpesh.tailornew.model.AllUser;
import com.example.kalpesh.tailornew.model.Allitemname;
import com.example.kalpesh.tailornew.model.Staff;
import com.example.kalpesh.tailornew.model.Ttask;
import com.example.kalpesh.tailornew.model.itemname;
import com.example.kalpesh.tailornew.model.user;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TailorinfosubmitActivity extends AppCompatActivity {

    EditText et_taskid;
    TextView tv_itemid, tv_userid, tv_itemrate, tv_itemname, tv_date, tv_contact;
    Button btn_find;
    ArrayList<String> auserid, aitemid, aimageurl, aitemrate, aitemname, contactno;
    String staffid, userid, taskid, tailorname, itemid, imageurl, itemname, itemrate, btn, dt, contact;
    ImageView img;
    Spinner spnselect, spnsp;
    LinearLayout llshirt, llpent;
    String trace = "";
    private SharedPreferences sharedPreferences;
    EditText et_slength, et_sshoulder, et_sbaay, et_scheast, et_scollor, et_sarm, et_sfront;
    EditText et_plength, et_pwaist, et_pseat, et_pbottom, et_pknee, et_pthign, et_plevel;
    String slength, sshoulder, sbaay, scheast, scollor, sarm, sfront;
    String plength, pwaist, pseat, pbottom, pknee, pthign, plevel;

    String clothing[] = {"Select clothing type", "Shirt Suit", "Shervani", "Jabho Lengho"};
    String sp[] = {"Select type", "Shirt", "Pent", "Shervani", "Suit", "Jabho", "Lengho"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailorinfosubmit);

        et_taskid = (EditText) findViewById(R.id.et_taskid);
        tv_userid = (TextView) findViewById(R.id.tv_userid);
        tv_itemid = (TextView) findViewById(R.id.tv_itemid);
        tv_itemname = (TextView) findViewById(R.id.tv_itemname);
        tv_itemrate = (TextView) findViewById(R.id.tv_rate);
        tv_date = (TextView) findViewById(R.id.tv_date);
        btn_find = (Button) findViewById(R.id.btn_find);
        img = (ImageView) findViewById(R.id.imgpic);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        spnselect = (Spinner) findViewById(R.id.spnselect);
        spnsp = (Spinner) findViewById(R.id.spnsp);
        llshirt = (LinearLayout) findViewById(R.id.llshirt);
        llpent = (LinearLayout) findViewById(R.id.llpent);

        et_slength = (EditText) findViewById(R.id.et_slength);
        et_sshoulder = (EditText) findViewById(R.id.et_sshoulder);
        et_sbaay = (EditText) findViewById(R.id.et_sbaay);
        et_scheast = (EditText) findViewById(R.id.et_scheast);
        et_scollor = (EditText) findViewById(R.id.et_scollor);
        et_sarm = (EditText) findViewById(R.id.et_sarm);
        et_sfront = (EditText) findViewById(R.id.et_sfront);
        et_plength = (EditText) findViewById(R.id.et_plength);
        et_pwaist = (EditText) findViewById(R.id.et_pwaist);
        et_pseat = (EditText) findViewById(R.id.et_pseat);
        et_pbottom = (EditText) findViewById(R.id.et_pbottom);
        et_pknee = (EditText) findViewById(R.id.et_pknee);
        et_pthign = (EditText) findViewById(R.id.et_pthign);
        et_plevel = (EditText) findViewById(R.id.et_plevel);


        spnselect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a = String.valueOf(spnselect.getSelectedItemPosition());
                //Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();
                if (a.equals("0")) {
                    spnsp.setVisibility(View.GONE);
                    llshirt.setVisibility(View.GONE);
                    llpent.setVisibility(View.GONE);
                } else {
                    spnsp.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a = String.valueOf(spnsp.getSelectedItemPosition());
                if (a.equals("0")) {
                    llshirt.setVisibility(View.GONE);
                    llpent.setVisibility(View.GONE);
                    trace = "no";
                } else if (a.equals("1") || a.equals("3") || a.equals("4") || a.equals("5")) {
                    llshirt.setVisibility(View.VISIBLE);
                    llpent.setVisibility(View.GONE);
                    trace = "1";
                } else if (a.equals("2") || a.equals("6")) {
                    llpent.setVisibility(View.VISIBLE);
                    llshirt.setVisibility(View.GONE);
                    trace = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        auserid = new ArrayList<String>();
        aitemid = new ArrayList<String>();
        aimageurl = new ArrayList<String>();
        aitemrate = new ArrayList<String>();
        aitemname = new ArrayList<String>();
        contactno = new ArrayList<String>();

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFRENCES, 0);
        tailorname = sharedPreferences.getString(LoginActivity.USERNAME, "");
        new GetTailorid().execute();
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskid = et_taskid.getText().toString().trim();
                btn = btn_find.getText().toString().trim();

                slength = et_slength.getText().toString().trim();
                sshoulder = et_sshoulder.getText().toString().trim();
                sbaay = et_sbaay.getText().toString().trim();
                scheast = et_scheast.getText().toString().trim();
                scollor = et_scollor.getText().toString().trim();
                sarm = et_sarm.getText().toString().trim();
                sfront = et_sfront.getText().toString().trim();
                plength = et_plength.getText().toString().trim();
                pwaist = et_pwaist.getText().toString().trim();
                pseat = et_pseat.getText().toString().trim();
                pbottom = et_pbottom.getText().toString().trim();
                pknee = et_pknee.getText().toString().trim();
                pthign = et_pthign.getText().toString().trim();
                plevel = et_plevel.getText().toString().trim();


                if (btn.equals("Fetch details")) {
                    if (taskid.equals("")) {
                        Toast.makeText(getApplicationContext(), "Enter TaskID", Toast.LENGTH_LONG).show();
                    } else {
                        new fetchdetails().execute();
                        btn = "Update";
                        btn_find.setText(btn);
                    }
                } else if (btn.equals("Update")) {
                    dt = tv_date.getText().toString().trim();
                    if (!dt.equals("Click for Delivery Date")) {
                        new Updatetas().execute();
                        btn = "Fetch details";
                        btn_find.setText(btn);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please select Delivery Date", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.show();
            }
        });

        Intent in = getIntent();
        Bundle b = in.getExtras();

        if (b != null) {
            String tid = b.getString("tid");

            et_taskid.setText(tid);
            et_taskid.setEnabled(false);
        }

        tv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Call", "SMS", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(TailorinfosubmitActivity.this);
                //builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Call")) {
                            //       final TextView phoneNumber = (TextView) findViewById(R.id.tv_contact);
                            String phoneNum = tv_contact.getText().toString();
                            if (!TextUtils.isEmpty(phoneNum)) {
                                String dial = "tel:" + phoneNum;
                                if (ActivityCompat.checkSelfPermission(TailorinfosubmitActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //   ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                            } else {
                                Toast.makeText(TailorinfosubmitActivity.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
                            }

                        } else if (items[item].equals("SMS")) {
                            Intent intent = new Intent(TailorinfosubmitActivity.this, SmsActivity.class);
                            intent.putExtra("cno", contact);
                            startActivity(intent);
                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
        spinner2meth();
        spinnermeth();
    }


    public <ViewGroup> void spinner2meth() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext().getApplicationContext(), android.R.layout.simple_list_item_1, clothing) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTextColor(Color.WHITE);
                v.setTextSize(15);
                return v;
            }

            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTextColor(Color.BLACK);
                v.setTextSize(15);
                return v;
            }
        };

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnselect.setAdapter(adapter1);
    }

    public <ViewGroup> void spinnermeth() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext().getApplicationContext(), android.R.layout.simple_list_item_1, sp) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTextColor(Color.WHITE);
                v.setTextSize(15);
                return v;
            }

            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTextColor(Color.BLACK);
                v.setTextSize(15);
                return v;
            }
        };

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnsp.setAdapter(adapter1);
    }

    public class InsertMeasure1 extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            String result = api.Measure1(slength, sshoulder, sbaay, scheast, scollor, sarm, sfront, staffid, userid);
            return result;
        }
    }

    public class InsertMeasure2 extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            String result = api.Measure2(plength, pwaist, pseat, pbottom, pknee, pthign, plevel, staffid, userid);
            return result;
        }
    }

    public class GetTailorid extends AsyncTask<Void, Void, AllStaff> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(TailorinfosubmitActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
        }

        @Override
        protected AllStaff doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            AllStaff result = api.GetTailorid(tailorname);
            return result;
        }

        @Override
        protected void onPostExecute(AllStaff allStaff) {
            if (allStaff != null) {
                for (Staff s : allStaff.getData()) {
                    staffid = s.getStaffid();
                }
            }
            dialog.dismiss();
            super.onPostExecute(allStaff);
        }
    }

    public class Updatetas extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(TailorinfosubmitActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            String result = api.Updatetask(taskid, staffid, dt);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if (trace.equals("0")) {
                    new InsertMeasure2().execute();
                } else if (trace.equals("1")) {
                    new InsertMeasure1().execute();
                }
            } else {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
            super.onPostExecute(s);
        }
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            tv_date.setText(format);
        }
    };

    public class fetchdetails extends AsyncTask<Void, Void, AllTtask> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(TailorinfosubmitActivity.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            auserid.clear();
            aitemid.clear();
            aimageurl.clear();
            aitemname.clear();
            aitemrate.clear();
            super.onPreExecute();
        }

        @Override
        protected AllTtask doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            AllTtask result = api.GetTaskdetai(taskid);
            return result;
        }

        @Override
        protected void onPostExecute(AllTtask allTtask) {
            if (allTtask != null) {
                for (Ttask t : allTtask.getData()) {
                    auserid.add(t.getUserid());
                    aitemid.add(t.getItemid());
                }
                userid = auserid.get(0);
                itemid = aitemid.get(0);
                tv_userid.setText("Userid " + userid);
                tv_itemid.setText("Itemid " + itemid);
                new GetContactno().execute();
                new GetItemImage().execute();
            }
            dialog.dismiss();

            super.onPostExecute(allTtask);
        }
    }

    public class GetContactno extends AsyncTask<Void, Void, AllUser> {

        @Override
        protected AllUser doInBackground(Void... voids) {
            contactno.clear();
            ApiCall api = new ApiCall();
            AllUser result = api.GetUserCont(userid);
            return result;
        }

        @Override
        protected void onPostExecute(AllUser allUser) {
            if (allUser != null) {
                for (user s : allUser.getData()) {
                    contactno.add(s.getContactno());
                }
                contact = contactno.get(0);
                tv_contact.setText(contact);
            }
            super.onPostExecute(allUser);
        }
    }

    public class GetItemImage extends AsyncTask<Void, Void, Allitemname> {

        @Override
        protected Allitemname doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            Allitemname result = api.GetData(itemid);
            return result;
        }

        @Override
        protected void onPostExecute(Allitemname allitemname) {
            if (allitemname != null) {
                for (itemname i : allitemname.getData()) {
                    aimageurl.add(i.getImage());
                    aitemname.add(i.getItemname());
                    aitemrate.add(i.getBasicrate());
                }
                imageurl = aimageurl.get(0);
                itemname = aitemname.get(0);
                itemrate = aitemrate.get(0);
                if (imageurl != "") {
                    Picasso.get()
                            .load(imageurl)
                            .into(img);
                }
                tv_itemname.setText(itemname);
                tv_itemrate.setText(itemrate);

            }
            super.onPostExecute(allitemname);
        }
    }
}
