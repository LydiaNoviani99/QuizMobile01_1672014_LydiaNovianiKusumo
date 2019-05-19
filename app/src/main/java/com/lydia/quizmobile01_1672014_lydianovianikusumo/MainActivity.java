package com.lydia.quizmobile01_1672014_lydianovianikusumo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lydia.quizmobile01_1672014_lydianovianikusumo.adapter.TourAdapter;
import com.lydia.quizmobile01_1672014_lydianovianikusumo.entity.Tours;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 1672014 - Lydia Noviani Kusumo
 */

public class MainActivity extends AppCompatActivity implements TourAdapter.DataClickedListener
{
    @BindView(R.id.spin_location)
    Spinner spinLocation;

    private ArrayList<String> locations;
    private ArrayAdapter<String> locationAdapter;


    @BindView(R.id.spin_category)
    Spinner spinCategory;

    private ArrayList<String> categories;
    private ArrayAdapter<String> categoryAdapter;


    @BindView(R.id.btn_Submit)
    Button btn_submit;
    @BindView(R.id.rv_data)
    RecyclerView rvTour;
    private TourAdapter tourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this, manager.getOrientation());
        rvTour.addItemDecoration(decoration);
        rvTour.setLayoutManager(manager);
        rvTour.setAdapter(getTourAdapter());
        populateTourData();

        locations = new ArrayList<>();
        locations.add("Amsterdam");
        locations.add("Barcelona");
        locations.add("Berlin");
        locations.add("Dubai");
        locations.add("London");
        locations.add("Paris");
        locations.add("Rome");
        locations.add("Tuscany");
        locationAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,locations);
        spinLocation.setAdapter(locationAdapter);

        categories = new ArrayList<>();
        categories.add("accommodation");
        categories.add("attraction");
        categories.add("restaurant");
        categories.add("poi");
        categoryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,categories);
        spinCategory.setAdapter(categoryAdapter);
    }

    public TourAdapter getTourAdapter() {
        if(tourAdapter == null){
            tourAdapter = new TourAdapter();
            tourAdapter.setListener(this);
        }
        return tourAdapter;
    }
    @Override
    public void onTourClickedListener(Tours tours) {
        loadTourData();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);
    }

    public void loadTourData(){
        final Uri uri = Uri.parse("http://tour-pedia.org/api/getPlaces")
                .buildUpon()
                .appendQueryParameter("location",spinLocation.getSelectedItem().toString())
                .appendQueryParameter("category",spinCategory.getSelectedItem().toString())
                .build();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = new JSONObject();
                Gson gson = new Gson();
                ArrayList<Tours> tours = new ArrayList<>();
                Tours[] arrTours = gson.fromJson(response, Tours[].class);
                tours.addAll(Arrays.asList(arrTours));
                //Toast.makeText(MainActivity.this, "Masuk", Toast.LENGTH_SHORT).show();
                getTourAdapter().setTours(tours);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error on Response", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    public void populateTourData(){
        final Uri uri = Uri.parse("http://tour-pedia.org/api/getPlaces")
                .buildUpon()
                .appendQueryParameter("location","Barcelona")
                .appendQueryParameter("category","accommodation")
                .build();
        System.out.println("asdadsdasdasdasdasdasdasdasdas" +uri);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                JSONObject object = new JSONObject();
//                Gson gson = new Gson();
//                ArrayList<Tours> tours = new ArrayList<>();
//                Tours[] arrTour = gson.fromJson(response, Tours[].class);
//                tours.addAll(Arrays.asList(arrTour));
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                getTourAdapter().setTours(tours);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error on Response", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    @OnClick(R.id.btn_Submit)
    void btnSubmitAction(){
        final Uri uri = Uri.parse("http://tour-pedia.org/api/getPlaces")
                .buildUpon()
                .appendQueryParameter("location",spinLocation.getSelectedItem().toString())
                .appendQueryParameter("category",spinCategory.getSelectedItem().toString())
                .build();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = new JSONObject();
                Gson gson = new Gson();
                ArrayList<Tours> tours = new ArrayList<>();
                Tours[] arrTours = gson.fromJson(response, Tours[].class);
                tours.addAll(Arrays.asList(arrTours));
                //Toast.makeText(MainActivity.this, "Masuk", Toast.LENGTH_SHORT).show();
                getTourAdapter().setTours(tours);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error on Response", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

}
