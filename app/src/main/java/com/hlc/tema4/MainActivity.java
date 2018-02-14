package com.hlc.tema4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    // Referencia al servicio de posicionamiento

    LocationManager locationManager;

    // Para almacenar la latitud y longitud

    double lat;

    double lon;

    TextView latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // referencias a las View

        latitud = (TextView) findViewById(R.id.tvLatitud2);

        longitud = (TextView) findViewById(R.id.tvLongitud2);

        // obtenemos el servicio de posicionamiento

        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (this.locationManager == null) {

            Toast.makeText(this, "Error al recuperar el GPS", Toast.LENGTH_LONG)

                    .show();

        }

        // registramos la recepción de datos del GPS

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.locationManager.requestLocationUpdates(

                LocationManager.GPS_PROVIDER, 30000, 20, (LocationListener) this);



    }



    public void onClickDondeEstoy(View view) {

        // Creamos el Intent para abrir la aplicación de mapas

        Intent i = new Intent();

        i.setAction(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("geo:" + this.lat + "," + this.lon);

        i.setData(uri);

        startActivity(i);

    }


/*
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.activity_main, menu);

        return true;



    }
*/


    @Override

    public void onLocationChanged(Location location) {

        // guardamos los valores de latitud y longitud

        this.lat = location.getLatitude();

        this.lon = location.getLongitude();

        // mostramos la posición en pantalla

        this.latitud.setText("Latitud: " + String.valueOf(lat));

        this.longitud.setText("Longitud: " + String.valueOf(lon));



    }



    @Override

    public void onProviderDisabled(String provider) {

        // TODO Auto-generated method stub



    }



    @Override

    public void onProviderEnabled(String provider) {

        // TODO Auto-generated method stub



    }



    @Override

    public void onStatusChanged(String provider, int status, Bundle extras) {

        // TODO Auto-generated method stub



    }



}