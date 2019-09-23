package com.example.soo.bungbung2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText rgrfullname=(EditText)findViewById(R.id.Rgrfullname);
        final EditText rgremail=(EditText)findViewById(R.id.Rgremail);
        final EditText rgrpassword=(EditText)findViewById(R.id.Rgrpassword);
        Button register=(Button)findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("fullName", rgrfullname.getText().toString());
                    postDataParam.put("email", rgremail.getText().toString());
                    postDataParam.put("password", rgrpassword.getText().toString());

                } catch (JSONException e) {
                    Log.e("등록창", "JSONEXception");
                }
                new InsertLoginData(Register.this).execute(postDataParam);

                Intent gologin=new Intent(getApplicationContext(),Login.class);
                startActivity(gologin);
            }
        });

        /////


    }
   

}
