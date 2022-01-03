package com.example.kalpesh.tailornew.Api;

import android.util.Log;

import com.example.kalpesh.tailornew.model.AllStaff;
import com.example.kalpesh.tailornew.model.AllTtask;
import com.example.kalpesh.tailornew.model.AllUser;
import com.example.kalpesh.tailornew.model.Allitemname;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ApiCall {
    public String AddItem(String itemname, String basicrate) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("itemname", itemname)
                .add("basicrate", basicrate);

        String url = ApiReso.ADDITEM;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public String Measure1(String length, String shoulder, String baay, String chest, String arm, String front, String collor, String tid, String uid) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("length", length)
                .add("shoulder", shoulder)
                .add("baay", baay)
                .add("chest", chest)
                .add("arm", arm)
                .add("front", front)
                .add("collor", collor)
                .add("tid",tid)
                .add("uid", uid);

        String url = ApiReso.ADDMEASSURE1;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }
    public String Measure2(String length, String waist,String seat,String bottom,String knee,String thigh,String level, String tid, String uid) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("length", length)
                .add("waist", waist)
                .add("seat", seat)
                .add("bottom", bottom)
                .add("knee", knee)
                .add("thigh", thigh)
                .add("level", level)
                .add("tid",tid)
                .add("uid", uid);

        String url = ApiReso.ADDMEASSURE2;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public Allitemname GetData() {
        Allitemname result = new Allitemname();
        HTTPCall httpCall = new HTTPCall();
        String output = httpCall.GET(ApiReso.GETITEMDETAILS);
        result = new Gson().fromJson(output, Allitemname.class);
        return result;
    }

    public Allitemname GetData(String id) {
        Allitemname result = new Allitemname();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETITEMIMAGE + "?id=" + id.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, Allitemname.class);
        return result;
    }

    public String AddUser(String uname, String gender, String address, String email, String password, String cno) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("uname", uname)
                .add("gender", gender)
                .add("address", address)
                .add("email", email)
                .add("password", password)
                .add("cno", cno);

        String url = ApiReso.ADDUSER;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public String Updatetask(String id, String staffid, String dt) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("id", id)
                .add("staffid", staffid)
                .add("dt", dt);

        String url = ApiReso.UPDATETASK;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public String userLogin(String name, String password) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("email", name)
                .add("password", password);

        String url = ApiReso.USERLOGIN;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public String TailorLogin(String name, String password) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("email", name)
                .add("password", password);

        String url = ApiReso.TAILORLOGIN;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

    public AllUser GetUserid(String name) {
        AllUser result = new AllUser();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETUSERID + "?name=" + name.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public AllStaff GetTailorid(String name) {
        AllStaff result = new AllStaff();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETTAILORID + "?name=" + name.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, AllStaff.class);
        return result;
    }

    public AllUser GetUserCont(String id) {
        AllUser result = new AllUser();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETUSERCONTACT + "?id=" + id.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }


    public AllTtask GetTaskdetai(String taskid) {
        AllTtask result = new AllTtask();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETTASKDE + "?id=" + taskid.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, AllTtask.class);
        return result;
    }


    public AllTtask GetTaskid(String staffid) {
        AllTtask result = new AllTtask();
        HTTPCall call = new HTTPCall();
        String url = ApiReso.GETTASKID + "?staffid=" + staffid.trim();
        Log.d("result", url);
        String output = call.GET(url);
        Log.d("result", output);
        result = new Gson().fromJson(output, AllTtask.class);
        return result;
    }


    public String Addtask(String uid, String extra, String itemid) {
        String result = "";
        HTTPCall call = new HTTPCall();
        FormBody.Builder formbuilder = new FormBody.Builder()
                .add("uid", uid)
                .add("extra", extra)
                .add("itemid", itemid);

        String url = ApiReso.ASSIGNTASK;
        RequestBody postData = formbuilder.build();
        result = call.POST(url, postData);
        Log.d("result", result);
        return result;
    }

}
