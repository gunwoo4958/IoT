package com.example.soo.bungbung2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final VideoView videoView=(VideoView)findViewById(R.id.video);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.gommov));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        Button btn=(Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Rent.class);
                startActivity(intent);
            }
        });




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
