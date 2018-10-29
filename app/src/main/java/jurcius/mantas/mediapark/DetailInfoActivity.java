package jurcius.mantas.mediapark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailInfoActivity extends AppCompatActivity {

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        car = (Car) intent.getSerializableExtra("car");

        TextView IDTV = findViewById(R.id.IDTV);
        TextView PlateNumberTV = findViewById(R.id.PlateNumberTV);
        TextView LocationIDTV = findViewById(R.id.LocationIDTV);
        TextView LatitudeTV = findViewById(R.id.LatitudeTV);
        TextView LongtitudeTV = findViewById(R.id.LongitudeTV);
        TextView AddressTV = findViewById(R.id.AddressTV);
        TextView ModelIDTV = findViewById(R.id.ModelIDTV);
        TextView ModelTitleTV = findViewById(R.id.ModelTitleTV);
        ImageView PhotoIV = findViewById(R.id.PhotoIV);
        TextView BatteryPercentageTV = findViewById(R.id.BatteryPercentageTV);
        TextView BatteryEstimatedDistance = findViewById(R.id.BatteryEstimatedDistanceTV);
        TextView IsChargingTV = findViewById(R.id.IsChargingTV);

        IDTV.setText(car.id);
        PlateNumberTV.setText(car.plateNumber);
        LocationIDTV.setText(car.location.id);
        LatitudeTV.setText(car.location.latitude);
        LongtitudeTV.setText(car.location.longitude);
        AddressTV.setText(car.location.address);
        ModelIDTV.setText(car.model.id);
        ModelTitleTV.setText(car.model.title);
        BatteryPercentageTV.setText(car.batteryPercentage);
        BatteryEstimatedDistance.setText(car.batteryEstimatedDistance);
        IsChargingTV.setText(car.isCharging);


        Glide.with(this).load(car.model.photoUrl).into(PhotoIV);
    }

    public void LocationButtonOnClick(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Specific", car);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_openList:
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_openMap:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_openFilter:
                intent = new Intent(this, FilterActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_actions, menu);
        return true;
    }
}
