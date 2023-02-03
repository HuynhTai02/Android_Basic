package com.tai.skeleton_shimmer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tai.skeleton_shimmer.databinding.ActivityMainContentPlaceholderBinding;

import org.json.JSONException;

public class Main_Act_Content extends AppCompatActivity {
    private ActivityMainContentPlaceholderBinding binding;

    // below line is the variable for url from
    // where we will be querying our data
    private String url = "https://jsonkeeper.com/b/63OH";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainContentPlaceholderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {

        // on below line we are
        // starting our shimmer layout.
        binding.shimmerLayout.startShimmer();
        getData();
    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Main_Act_Content.this);
        // this is the error listener method which
        // we will call if we get any error from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // inside the on response method.(phản hồi)
            // in below line we are making our card
            // view visible after we get all the data.
            // on below line we are making our shimmer
            // layout visibility to gone.
            // on below line we are stopping our shimmer.
            binding.shimmerLayout.stopShimmer();
            binding.idCVCourse.setVisibility(View.VISIBLE);
            try {
                // now we get our response from API in json object format(nhận phản hồi từ API Json)
                // in below line we are extracting a string with its key
                // value from our json object.
                // similarly we are extracting all the strings from our json object.
                String courseName = response.getString("courseName");
                String courseTracks = response.getString("courseTracks");
                String courseMode = response.getString("courseMode");
                String courseImageURL = response.getString("courseimg");

                // after extracting all the data we are
                // setting that data to all our views.
                binding.idTVCourseName.setText(courseName);
                binding.idTVTracks.setText(courseTracks);
                binding.idTVBatch.setText(courseMode);

                // we are using picasso to load the image from url.
                Picasso.get().load(courseImageURL).into(binding.idIVCourse);
            } catch (JSONException e) {
                // if we do not extract data from json object properly.
                // below line of code is use to handle json exception
                e.printStackTrace();
            }
        }, error -> Toast.makeText(Main_Act_Content.this, "Fail to get data..", Toast.LENGTH_SHORT).show());
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        queue.add(jsonObjectRequest);
    }
}
