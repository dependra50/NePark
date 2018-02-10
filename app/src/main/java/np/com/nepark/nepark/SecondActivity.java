package np.com.nepark.nepark;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView parking;
    private ParkingListAdapter adapter;
    private List<Parking> ParkingList;
    private boolean success=false;
    double lat1,lon1;
    private int range;


//    String[] myData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       range=getIntent().getIntExtra("ranges",100);

        //for gps traker
        ActivityCompat.requestPermissions(SecondActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        GpsTracker g=new GpsTracker(getApplicationContext());
        Location l=g.getLocation();
        if(l!=null)
        {
            lat1=l.getLatitude();
            lon1=l.getLongitude();

          //  Toast.makeText(getApplicationContext(),"LAT:"+lat1+"\n LON:"+lon1,Toast.LENGTH_LONG).show();
        }



        parking=(ListView)findViewById(R.id.listView);
        ParkingList=new ArrayList<>();


       // ParkingList.add(new Parking(1,"Nepal Parking",20,10));
        //ParkingList.add(new Parking(1,"Ratna Parking",20,10));
        //ParkingList.add(new Parking(1,"Bheri Parking",20,10));
        //ParkingList.add(new Parking(1,"Koshi Parking",20,10));
        //ParkingList.add(new Parking(1,"Rapti Parking",20,10));
        //ParkingList.add(new Parking(1,"Nepalgunj Parking",20,10));



        Retrieve fetch=new Retrieve();
        fetch.execute("");

//adapter=new ParkingListAdapter(getApplicationContext(),ParkingList);
//parking.setAdapter(adapter);


        parking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id;
                id=(Integer)view.getTag();

               Intent map=new Intent(SecondActivity.this,MapsActivity.class);
                map.putExtra("parkinglots",id);
                startActivity(map);

                //Toast.makeText(getApplicationContext(),"Clicked Parking lot Id is: "+id,Toast.LENGTH_SHORT).show();
            }
        });


        //ListView myListView=(ListView)findViewById(R.id.listView);
       // ArrayAdapter myArrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,myData);
       // myListView.setAdapter(myArrayAdapter);
    }

    private class Retrieve extends AsyncTask<String,String,String>
    {
        String msg="";


        //generate the overrid method by pressing ctrl + O


        @Override
        protected void onPreExecute() {
           msg="Data is fetching from database";
           //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        }



        @Override
        protected String doInBackground(String... strings) {

            //press ctrl+alt+t for try catch


            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection("jdbc:mysql://192.168.100.5/nepark","deepak","deepak");



                if(conn==null)
                {
                    msg="coulnot connect to database";
                    success=false;
                }else
                {
                    String query="SELECT *FROM list";
                    Statement stm=conn.createStatement();
                    ResultSet rs=stm.executeQuery(query);

                    if(rs!=null)
                    {

                        while(rs.next())
                        {
                               {
                                   try {


                                       if(distance(lat1,lon1,(double)rs.getDouble("latitude"),(double)rs.getDouble("longitude"))<=range) {
                                       ParkingList.add(new Parking(rs.getInt("id"), rs.getString("name"), rs.getString("location"), rs.getInt("available"),rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getInt("rate")));
                                       }
                                   } catch (Exception ex) {
                                       ex.printStackTrace();
                                   }
                               }

                        }
                        msg="Data successfully found";
                        success=true;

                    }else
                    {
                        msg="data couldnot found";
                    }

                }
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            s=msg;

            if(success==false){

            }else {


                try {
                    ParkingListAdapter adapter = new ParkingListAdapter(getApplicationContext(), ParkingList);
                    parking.setChoiceMode(parking.CHOICE_MODE_MULTIPLE);
                    parking.setAdapter(adapter);

                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }

        }
    }



    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
