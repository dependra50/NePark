package np.com.nepark.nepark;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText range;
    TextView auto;
    int r;
    double latitude,longitude;
    String str="your Current Location";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);


        //for location


      ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        GpsTracker g=new GpsTracker(getApplicationContext());
        Location l=g.getLocation();
        if(l!=null)
        {
            latitude=l.getLatitude();
            longitude=l.getLongitude();

            //Toast.makeText(getApplicationContext(),"LAT:"+latitude+"\n LON:"+longitude,Toast.LENGTH_LONG).show();
        }





        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            str = addressList.get(0).getAddressLine(0) ;
            // str +=addressList.get(0).getCountryName();



        } catch (IOException e) {
            e.printStackTrace();
        }



        auto.setText(str);






        range=(EditText)findViewById(R.id.seekBar);

        Button submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // range=(EditText)findViewById(R.id.seekBar);
               // r=Integer.parseInt(range.getText().toString());
                submit();



            }
        });

    }

    public void submit()
    {
        intializer();
        if(!validate())
        {
            Toast.makeText(getApplicationContext(),"Enter validate range",Toast.LENGTH_SHORT).show();;
        }
        else
        {
            Intent  intent=new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("ranges",r);
            startActivity(intent);

        }
    }

    public boolean validate()
    {
        boolean validate=true;
        if(r>10000||r==0||range.getText().toString().trim().length()==0||range.getText().toString().isEmpty()||range.getText().toString().trim()=="RANGE IN KM"){
            range.setError("Enter the valid range..");
            validate=false;
        }
        return validate;


    }


    public void intializer()
    {
        r=Integer.parseInt(range.getText().toString());
    }


}
