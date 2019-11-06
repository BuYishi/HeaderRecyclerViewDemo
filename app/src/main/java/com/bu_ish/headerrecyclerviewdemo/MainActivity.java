package com.bu_ish.headerrecyclerviewdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvChapter;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvChapter = findViewById(R.id.rvChapter);
        rvChapter.setLayoutManager(new LinearLayoutManager(this));
        String json = FileUtils.readAssetFileAsString(this, "JsonStub.json");
        Log.e(TAG, "json: " + json);
        CourseChaptersData data = new Gson().fromJson(json, CourseChaptersData.class);
        rvChapter.setAdapter(new ChapterHeaderRecyclerViewAdapter(data.getList()));
    }

}