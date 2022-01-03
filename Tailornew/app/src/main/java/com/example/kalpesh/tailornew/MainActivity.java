package com.example.kalpesh.tailornew;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalpesh.tailornew.Api.ApiCall;
import com.example.kalpesh.tailornew.Api.ApiReso;
import com.example.kalpesh.tailornew.adapter.CustomList;
import com.example.kalpesh.tailornew.adapter.CustomListView;
import com.example.kalpesh.tailornew.helper.GetAlImages;
import com.example.kalpesh.tailornew.model.Allitemname;
import com.example.kalpesh.tailornew.model.itemname;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_additem;
    private ListView listView;
    private ArrayList<itemname> FSList;
    private ListAdapter adapter;
    String image;
    TextView tv_logout;

//    public static final String GET_IMAGE_URL="http://192.168.43.187/Tailornew/Getitem.php";

    //  public GetAlImages getAlImages;

    //  public static final String BITMAP_ID = "BITMAP_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv_displayitem);
        tv_logout=(TextView)findViewById(R.id.tv_logout);
        FSList = new ArrayList<itemname>();
        adapter = new ListAdapter();
        listView.setAdapter(adapter);

        new getdetails().execute();

/*        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView=new CustomListView(this,itemname,basicrate,imagepath);
        listView.setAdapter(customListView);*/
        //    listView = (ListView) findViewById(R.id.lv_displayitem);
        //listView.setOnItemClickListener(this);
        //  getURLs();


        btn_additem = (Button) findViewById(R.id.btn_additem);
        btn_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdditemActivity.class));
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // String itemname = ((TextView) view.findViewById(R.id.tv_itemname)).getText().toString();
                //String rate = ((TextView) view.findViewById(R.id.tv_rate)).getText().toString();
                String itemname=FSList.get(i).getItemname();
                String rate=FSList.get(i).getBasicrate();
                String uri=FSList.get(i).getImage();
                String itemid=FSList.get(i).getItemid();
                startActivity(new Intent(MainActivity.this,DetailActivity.class)
                .putExtra("itemname",itemname)
                .putExtra("rate",rate)
                .putExtra("uri",uri)
                .putExtra("itemid",itemid));
                finish();

            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(LoginActivity.MyPREFRENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    public class getdetails extends AsyncTask<Void, Void, Allitemname> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading..");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Allitemname doInBackground(Void... voids) {
            Allitemname result = new Allitemname();
            result = new ApiCall().GetData();
            return result;
        }

        @Override
        protected void onPostExecute(Allitemname allitemname) {

            if (allitemname != null) {
                FSList.clear();
                FSList.addAll(allitemname.getData());
                adapter.notifyDataSetChanged();
            }
            super.onPostExecute(allitemname);
            dialog.dismiss();
        }
    }

    public class ListAdapter extends BaseAdapter {
        private ViewHolder Holder;

        @Override
        public int getCount() {
            return FSList.size();
        }

        @Override
        public Object getItem(int i) {
            return FSList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, viewGroup, false);

                Holder = new ViewHolder();

                Holder.itemname = (TextView) view.findViewById(R.id.tv_itemname);
                Holder.basicrate = (TextView) view.findViewById(R.id.tv_rate);
                Holder.imageview = (ImageView) view.findViewById(R.id.imgpic);

                view.setTag(Holder);
            } else {
                Holder = (ViewHolder) view.getTag();
            }

            Holder.itemname.setText(FSList.get(i).getItemname());
            Holder.basicrate.setText(FSList.get(i).getBasicrate());

            image = FSList.get(i).getImage();

            if (image != "") {
                Picasso.get()
                        .load(/*ApiReso.BASE_URL +*/image)
                        .fit()
                        .error(R.drawable.login)
                        .into(Holder.imageview);
            }
            return view;

        }

        private class ViewHolder {
            TextView itemname, basicrate;
            ImageView imageview;
        }

    }

 /*   private void getImages(){
        class GetImages extends AsyncTask<Void,Void,Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Downloading images...","Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                //Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();
                CustomList customList = new CustomList(MainActivity.this,GetAlImages.imageURLs,GetAlImages.bitmaps);//,GetAlImages.itemname,GetAlImages.basicrate);
                listView.setAdapter(customList);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlImages.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Loading...","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getAlImages = new GetAlImages(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(GET_IMAGE_URL);
    }*/


}
//for passing image id
   /* Intent intent = new Intent(this, ViewFullImage.class);
        intent.putExtra(BITMAP_ID,i);
                startActivity(intent);*/

//for displaying image
/*   Intent intent = getIntent();
    int i = intent.getIntExtra(ImageListView.BITMAP_ID,0);

        imageView = (ImageView) findViewById(R.id.imageViewFull);
                imageView.setImageBitmap(GetAlImages.bitmaps[i]);*/

