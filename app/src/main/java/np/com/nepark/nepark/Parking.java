package np.com.nepark.nepark;

/**
 * Created by dependra on 1/9/2018.
 */

public class Parking {
    private int id;
    private String lot;
    private String location;
    private int available;
    private double latitude;
    private double longitude;
    private int rate;

    public Parking(int id,String lot,String location,int available,double latitude,double longitude,int rate){
        this.id=id;
        this.lot=lot;
        this.available=available;
        this.latitude=latitude;
        this.longitude=longitude;
        this.rate=rate;
        this.location=location;
    }

    //setter and getter

    public void setId(int id)
    {
        this.id=id;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }


    public void setAvailable(int available) {
        this.available = available;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void setRate(int rate) {
        this.rate = rate;
    }




    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLot() {
        return lot;
    }

    public int getAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getRate() {
        return rate;
    }

}
