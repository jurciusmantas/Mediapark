package jurcius.mantas.mediapark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterActivity extends AppCompatActivity {

    private List<Car> carsList;
    private ListView listView;
    private String plateNumberFilter;
    private String[] cars;
    private String[] plates;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getCarsList();

        listView = findViewById(R.id.listViewCars);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailInfoActivity.class);
                intent.putExtra("car", carsList.get(position));
                startActivity(intent);
            }
        });

        EditText editText = findViewById(R.id.editPlateNumber);
        plateNumberFilter = editText.getText().toString();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (FilterActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
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

                adapter = new CarAdapter(getApplicationContext(), carsList);
                listView.setAdapter(adapter);
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
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_openFilter:
                Toast.makeText(getApplicationContext(),
                        "Filter is opened", Toast.LENGTH_LONG).show();
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
