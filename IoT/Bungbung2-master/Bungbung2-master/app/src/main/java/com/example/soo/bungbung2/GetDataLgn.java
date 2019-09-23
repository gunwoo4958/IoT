package com.example.soo.bungbung2;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetDataLgn extends GetRequest{
    public GetDataLgn(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        //EditText server =  activity.findViewById(R.id.server);
        String serverURLStr = "http://13.125.246.86:3000/api/authenticate";
        try {
            url = new URL(serverURLStr);  // 여기서 AWS 주소를 넣어야 한다.
            Log.e("url잘넣었나", "출력");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("Mal에러", "출력");
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        if (jsonString == null) {
            Log.e("jsonString이 null", "출력");
            return;
        }
//        ArrayList<Logininfo> arrayList = getArrayListFromJSONString(jsonString);

if(getArrayListFromJSONString(jsonString)){
    Toast.makeText(activity.getApplicationContext(), "Login Succes", Toast.LENGTH_SHORT).show();
    Log.e("로그인성공", "출력");
    Intent intent1=new Intent(activity.getApplicationContext(),MainActivity.class);
    activity.startActivity(intent1);
}
if(getArrayListFromJSONString(jsonString)==false) {
    Log.e("로그인실패", "출력");
    Toast.makeText(activity.getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
}
//        textView.setText(arrayList.indexOf(0));

    }


    protected boolean getArrayListFromJSONString(String jsonString) {
        ArrayList<Logininfo> output = new ArrayList();
        boolean loginresult=false;
        try {
            Log.e("getArray그 함수", "출력");
            JSONObject object =new JSONObject(jsonString);
//            JSONArray jsonArray = new JSONArray(jsonString);
            JSONArray jsonArray = object.getJSONArray("user");
            for (int i = 0; i < jsonArray.length(); i++) {
                Log.e("찾는중", "출력");
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                EditText email=(EditText)activity.findViewById(R.id.email);
                EditText password=(EditText)activity.findViewById(R.id.password);
                Log.e("login", email.getText().toString()+"과 "+jsonObject.getString("email"));
                Log.e("login", password.getText().toString()+"과 "+jsonObject.getString("password"));
                if(email.getText().toString().equals(jsonObject.getString("email"))&&password.getText().toString().equals(jsonObject.getString("password"))){

                    loginresult=true;
                    Log.e("login", String.valueOf(loginresult));
                }

            }
        } catch (JSONException e) {
            Log.e("JSON에러", "출력");
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return loginresult;
    }
}
