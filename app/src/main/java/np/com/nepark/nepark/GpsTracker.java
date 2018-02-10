package np.com.nepark.nepark;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by dependra on 1/8/2018.
 */

public class GpsTracker implements LocationListener{

    Context context;

    GpsTracker(Context c){
        context=c;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLocation(){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context,"Permission is not granted",Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager lm= (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        boolean isGPSEnabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled)
        {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);
            Location l=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else
        {


            //Toast.makeText(context,"Please Enabled Location",Toast.LENGTH_SHORT ).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();

    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }




}
