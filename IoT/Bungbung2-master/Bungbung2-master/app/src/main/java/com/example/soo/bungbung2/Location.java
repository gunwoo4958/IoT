package com.example.soo.bungbung2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.soo.bungbung2.MyAdapter.xx;

import static com.example.soo.bungbung2.MyAdapter.yy;

public class Location extends AppCompatActivity  implements OnMapReadyCallback {
    final int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION=0;
    private FusedLocationProviderClient mFusedLocationClient;
    android.location.Location mCurrentLocation;
    GoogleMap mGoogleMap;
    TextView addressTextView;
    //a마지막 geocoder 이용해서 input이용해서 이름에 매칭되는 addresses리스트 리턴 그중에서 첫번째get0을 가져와 bestReult가져오고 위도경도 얻어옴
//그결과값 바탕으로 마커로 표시하고 그위치로 지도를 이동

    Intent intent = getIntent();
//    String xvalue=intent.getStringExtra("x");
//    String yvalue=intent.getStringExtra("y");
//    Double x ;
//    Double y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetData2(Location.this).execute();
         View header;
        header= getLayoutInflater().inflate(R.layout.activity_rent,null,false);
        ListView txtList = (ListView)header.findViewById(R.id.listview);
//        txtList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Adapter adapter = adapterView.getAdapter();
//                JSONObject postDataParam = new JSONObject();
//                try {
//                    postDataParam.put("id", ((Contents)adapter.getItem(i)).id);
//                } catch (JSONException e) {
//                    Log.e("listview", "JSONEXception");
//                }
//
//
//            }
//        });
//        x=Double.valueOf(intent.getStringExtra("x"));
//        y=Double.valueOf(intent.getStringExtra("y"));
        setContentView(R.layout.activity_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (!checkLocationPermissions()) {
            requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
        } else {
            getLastLocation();
        }
//        Button btn=(Button)findViewById(R.id.button);
////        final EditText txt=(EditText)findViewById(R.id.edit_text) ;
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (txt.getText().equals("한성대입구역")) {
////
////                    Toast.makeText(getApplicationContext(),"한성대입구역을 선택하셨습니다", Toast.LENGTH_SHORT).show();
////                }
//                getAddress();
//
//
//            }
//        });

    }
    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                Location.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }




    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                    // 권한 획득 후 수행할 일: 예, getLastLocation();
                } else {
                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();       // Task<Location> 객체 반환
        task.addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                // Got last known location. In some rare situations this can be null.
                Log.i("ddd","fonud");
                if (location != null) {
                    mCurrentLocation = location;
                    Toast.makeText(getApplicationContext(),
                            "위도"+mCurrentLocation.getLatitude(),
                            Toast.LENGTH_SHORT)
                            .show();
                    //  updateUI();
                    LatLng newLocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                    if (mGoogleMap != null)
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation,15));
                }
//                else
//
//                    Toast.makeText(getApplicationContext(),
//                            "No Location Detected",
//                            Toast.LENGTH_SHORT)
//                            .show();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mGoogleMap = googleMap;
        new GetData2(Location.this).execute();

//        LatLng hansung = new LatLng(37.5817891, 127.009854);
        LatLng hansung = new LatLng(xx, yy);
//        TextView gps=(TextView)findViewById(R.id.gpsvalue);
//        Log.i("hihi", (String) gps.getText());
//        String a= String.valueOf(gps.getText());
//        String a="37.5817891";
//        Double aa=Double.valueOf(a);
//        TextView gps2=(TextView)findViewById(R.id.gpsvalue2);

//        String b=String.valueOf(gps2.getText());
//        String b="127.009854";
//        Double bb=Double.valueOf(b);
//        Log.i("hihi",String.valueOf(bb));
//        LatLng hansung = new LatLng(37.5817891, 127.009854);

//
//        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hansung,15));

//        googleMap.addMarker(new MarkerOptions().position(hansung).title("한성대학교"));
//        // move the camera
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hansung));
        addressTextView = (TextView) findViewById(R.id.result);
        addressTextView.setText("(x="+String.valueOf(xx)+", y="+String.valueOf(yy)+")");


        mGoogleMap.addMarker(
                new MarkerOptions().
                        position(hansung).title("1번킥보드")
        );
    }
    //******************************************주소얻기******************************
    private void getAddress() {



//        EditText address = (EditText) findViewById(R.id.edit_text);
//        try {
//            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
//            List<Address> addresses = geocoder.getFromLocationName(address.getText().toString(),1);
//            if (addresses.size() >0) {
//                Address bestResult = (Address) addresses.get(0);
//
////                addressTextView.setText(String.format("[ %s , %s ]",
////                        bestResult.getLatitude(),
////                        bestResult.getLongitude()));
////                addressTextView.setText("("+xvalue+", "+yvalue+")");
//                LatLng location = new LatLng(xx,yy);
//                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
//                mGoogleMap.addMarker(
//                        new MarkerOptions().
//                                position(location).
//                                title(address.getText().toString()));
//            }
//        } catch (IOException e) {
//            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
//            return;
//        }

    }
}