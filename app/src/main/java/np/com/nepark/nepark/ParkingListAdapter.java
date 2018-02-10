package np.com.nepark.nepark;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.util.ArraySet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

import static android.media.CamcorderProfile.get;

/**
 * Created by dependra on 1/9/2018.
 */

public class ParkingListAdapter extends BaseAdapter {

    private Context context;
    private List<Parking> ParkingList;



    //constructor

Set<View>viewSet;
    public ParkingListAdapter(Context context, List<Parking> ParkingList) {
        this.context = context;
        this.ParkingList = ParkingList;
        viewSet=new ArraySet<View>();

    }

    @Override
    public int getCount() {

        return ParkingList.size();
    }

    @Override
    public Object getItem(int i) {
        return ParkingList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context,R.layout.custom_item_layout,null);
        TextView lot=(TextView) v.findViewById(R.id.parkingLot);
        TextView total=(TextView) v.findViewById(R.id.total);
        TextView available=(TextView) v.findViewById(R.id.available);
        TextView rate=(TextView)v.findViewById(R.id.price);

        lot.setText(ParkingList.get(i).getLot());
        total.setText(ParkingList.get(i).getLocation());
        available.setText(String.valueOf(ParkingList.get(i).getAvailable()));
        rate.setText(String.valueOf(ParkingList.get(i).getRate()));

        viewSet.add(view);//to prevent from duplicating

        //save product id to tag
        //v.setTag(ParkingList.get(i).getLot());
        v.setTag(ParkingList.get(i).getId());
        //v.setTag(ParkingList.get(i).getLongitude());
        return v;
    }


}
