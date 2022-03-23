package com.example.platdujour.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.platdujour.R;


public class MapaLoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_loc);

        ActivityCompat.requestPermissions(MapaLoc.this, new String[]{Manifest.permission.SEND_SMS,
        Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(MapaLoc.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE}, PackageManager.PERMISSION_GRANTED);

    }
    public void bIGPS(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_NETWORK_STATE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MapaLoc.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MapaLoc.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MapaLoc.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return ;
        }

        LocationManager  mLocManager  = (LocationManager) getSystemService(MapaLoc.this.LOCATION_SERVICE);
        LocationListener mLocListener = new MinhaLocalizacao();

        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);






       if (mLocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            String texto = "Latitude.: " + MinhaLocalizacao.latitude + "\n" +
                    "Longitude: " + MinhaLocalizacao.longitude + "\n";
            Toast.makeText(MapaLoc.this, texto, Toast.LENGTH_LONG).show();
       } else {
            Toast.makeText(MapaLoc.this, "GPS DESABILITADO.", Toast.LENGTH_LONG).show();
        }
    }



    MinhaLocalizacao loc = new MinhaLocalizacao();

    public void sms(View v) {
        String numero = "43998056049";
        String mensagem = loc.n;
        try {
            SmsManager mySmsManager = SmsManager.getDefault();
            mySmsManager.sendTextMessage(" +5543998056049", null, mensagem, null, null);
            Toast.makeText(getApplicationContext(), "Mensagem Enviada.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensagem não Enviada.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}


