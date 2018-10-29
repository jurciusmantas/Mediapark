package jurcius.mantas.mediapark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void OpenListButtonOnClick(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void OpenMapButtonOnClick(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("AllCars","");
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
                Toast.makeText(getApplicationContext(),
                        "Home page is opened", Toast.LENGTH_LONG).show();
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
