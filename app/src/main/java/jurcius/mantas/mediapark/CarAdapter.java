package jurcius.mantas.mediapark;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CarAdapter extends ArrayAdapter<Car> {

    private Context mContext;
    private List<Car> mCarsList;
    private StringBuilder sb;

    public CarAdapter(@NonNull Context context, List<Car> list) {
        super(context, 0 , list);
        mContext = context;
        mCarsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Car currentCar = mCarsList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        sb = new StringBuilder();
        sb.append("Title : ");
        sb.append(currentCar.model.title);
        name.setText(sb.toString());


        TextView release = (TextView) listItem.findViewById(R.id.textView_batteryPercentage);
        sb = new StringBuilder();
        sb.append("Battery percentage : ");
        sb.append(currentCar.batteryPercentage);
        release.setText(sb.toString());

        TextView distance = (TextView) listItem.findViewById(R.id.textView_distance);
        sb = new StringBuilder();
        sb.append("Plate number : ");
        sb.append(currentCar.plateNumber);
        distance.setText(sb.toString());

        return listItem;
    }
}
