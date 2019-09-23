package com.example.soo.bungbung2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email=(EditText)findViewById(R.id.email);
        EditText password=(EditText)findViewById(R.id.password);
        Button Lgn=(Button)findViewById(R.id.btnLogin);
        Lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetDataLgn(Login.this).execute();
            }
        });
        Button register=(Button)findViewById(R.id.btnLinkToRegisterScreen);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(getApplicationContext(),Register.class);
                startActivity(register);
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.home:
//                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//                return true;
//
////            case R.id.location:
////                Intent intent1=new Intent(getApplicationContext(),Location.class);
////                startActivity(intent1);
////                return true;
//
//
//
//            case R.id.login:
//                Intent intent2=new Intent(getApplicationContext(),Login.class);
//                startActivity(intent2);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }
}
