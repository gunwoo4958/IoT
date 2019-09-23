package com.example.soo.bungbung2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

public class Rent extends AppCompatActivity {
private View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        Log.e("hi", "test1");
        new GetData(Rent.this).execute();
        Log.e("hi", "test2");
        ListView txtList = (ListView) findViewById(R.id.listview);
        txtList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("id", ((Contents)adapter.getItem(i)).id);
                } catch (JSONException e) {
                    Log.e("listview", "JSONEXception");
                }


            }
        });
//        header= getLayoutInflater().inflate(R.layout.adapter,null,false);
//        Button location=(Button)header.findViewById(R.id.button2);
//        final Button rent=(Button)header.findViewById(R.id.button5);
//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getApplicationContext(),Location.class);
//                startActivity(intent);
//            }
//        });
//        rent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(rent.getText().equals("대여하기")){
//                    rent.setText("반납하기");
//                }
//                if(rent.getText().equals("반납하기")){
//                    rent.setText("대여하기");
//                }
//            }
//        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;

//            case R.id.location:
//                Intent intent1=new Intent(getApplicationContext(),Location.class);
//                startActivity(intent1);
//                return true;



            case R.id.login:
                Intent intent2=new Intent(getApplicationContext(),Login.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
