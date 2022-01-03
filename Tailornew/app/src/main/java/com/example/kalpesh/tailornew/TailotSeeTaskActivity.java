package com.example.kalpesh.tailornew;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kalpesh.tailornew.Api.ApiCall;
import com.example.kalpesh.tailornew.model.AllStaff;
import com.example.kalpesh.tailornew.model.AllTtask;
import com.example.kalpesh.tailornew.model.Staff;
import com.example.kalpesh.tailornew.model.Ttask;

import java.util.ArrayList;

public class TailotSeeTaskActivity extends AppCompatActivity {

    ListView lv_data;
    ArrayList<Ttask> list;
    ListAdapter adapter;
    String tailorname, staffid;
    private ProgressDialog dialog;
    TextView tv_logout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailot_see_task);

        lv_data = (ListView) findViewById(R.id.lv_data);
        tv_logout=(TextView)findViewById(R.id.tv_logout);
        list = new ArrayList<Ttask>();

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFRENCES, 0);
        tailorname = sharedPreferences.getString(LoginActivity.USERNAME, "");

        adapter = new ListAdapter();

        lv_data.setAdapter(adapter);

        new GetTailorid().execute();

        lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tid = list.get(i).getTaskid();

                startActivity(new Intent(TailotSeeTaskActivity.this, TailorinfosubmitActivity.class)
                        .putExtra("tid", tid));
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(LoginActivity.MyPREFRENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(TailotSeeTaskActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    public class GetTailorid extends AsyncTask<Void, Void, AllStaff> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(TailotSeeTaskActivity.this);
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
                    new GetTaskIdd().execute();
                }
            }
            //dialog.dismiss();
            super.onPostExecute(allStaff);
        }
    }

    public class GetTaskIdd extends AsyncTask<Void, Void, AllTtask> {

        @Override
        protected AllTtask doInBackground(Void... voids) {
            ApiCall api = new ApiCall();
            AllTtask result = api.GetTaskid(staffid);
            return result;
        }

        @Override
        protected void onPostExecute(AllTtask allTtask) {
            if (allTtask != null) {
                list.clear();
                list.addAll(allTtask.getData());
                adapter.notifyDataSetChanged();
            }
            dialog.dismiss();
            super.onPostExecute(allTtask);
        }
    }


    public class ListAdapter extends BaseAdapter {
        private Viewholder holder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item_for_tailor_task, viewGroup, false);

                holder = new Viewholder();

                holder.tv_taskid = (TextView) view.findViewById(R.id.tv_taskid);

                view.setTag(holder);
            } else {
                holder = (Viewholder) view.getTag();
            }

            holder.tv_taskid.setText(list.get(i).getTaskid());
            return view;
        }

        private class Viewholder {
            TextView tv_taskid;
        }
    }
}
