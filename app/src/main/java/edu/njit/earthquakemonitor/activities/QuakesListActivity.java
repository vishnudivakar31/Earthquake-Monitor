package edu.njit.earthquakemonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.njit.earthquakemonitor.R;
import edu.njit.earthquakemonitor.model.EarthQuake;
import edu.njit.earthquakemonitor.util.Constants;

public class QuakesListActivity extends AppCompatActivity {

    private List<String> arrayList;
    private ListView listView;
    private RequestQueue requestQueue;
    private ArrayAdapter<String> arrayAdapter;
    private List<EarthQuake> earthQuakeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes_list);

        earthQuakeList = new ArrayList<>();
        listView = findViewById(R.id.listView);

        requestQueue = Volley.newRequestQueue(this);

        arrayList = new ArrayList<>();

        getAllQuakes(Constants.URL);
    }

    private void getAllQuakes(String url) {
        EarthQuake earthQuake = new EarthQuake();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray features = response.getJSONArray("features");
                    for(int i = 0; i < Constants.LIMIT && i < features.length(); i++) {
                        JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
                        JSONObject geometry = features.getJSONObject(i).getJSONObject("geometry");
                        JSONArray coordinates = geometry.getJSONArray("coordinates");
                        Double lon = coordinates.getDouble(0);
                        Double lat = coordinates.getDouble(1);

                        earthQuake.setPlace(properties.getString("place"));
                        earthQuake.setType(properties.getString("type"));
                        earthQuake.setTimestamp(properties.getLong("time"));
                        earthQuake.setMagnitude(properties.getDouble("mag"));
                        earthQuake.setDetailLink(properties.getString("detail"));
                        earthQuake.setLat(lat);
                        arrayList.add(earthQuake.getPlace());
                    }

                    arrayAdapter = new ArrayAdapter<>(QuakesListActivity.this, android.R.layout.simple_list_item_1,
                            android.R.id.text1, arrayList);

                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
