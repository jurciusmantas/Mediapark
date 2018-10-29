package jurcius.mantas.mediapark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Car specificCar;
    private List<Car> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("Specific") != null){
            specificCar = (Car) intent.getSerializableExtra("Specific");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng car1;

        if (specificCar != null){
            car1 = new LatLng(Double.parseDouble(specificCar.location.latitude),
                    Double.parseDouble(specificCar.location.longitude));
            mMap.addMarker(new MarkerOptions().position(car1).title("1st car"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(car1, 13.0f));
            return;
        }

        if (carsList != null){
            for (Car car : carsList) {
                car1 = new LatLng(Double.parseDouble(car.location.latitude),
                        Double.parseDouble(car.location.longitude));
                mMap.addMarker(new MarkerOptions().position(car1).title(""));
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(54.687157, 25.279652), 11.5f)); // Vilnius
            return;
        }

        else getCarsList();

    }


    private void getCarsList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Car>> call = api.getCars();

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                carsList = response.body();

                onMapReady(mMap);
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
            }
        });

        return;
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
                if (specificCar != null){
                    intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(),
                        "Map is opened", Toast.LENGTH_LONG).show();

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
