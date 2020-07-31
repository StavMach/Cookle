package in.tvac.akshayejh.firebasesearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class NameSearchResults extends AppCompatActivity {
    private static final String TAG = "NameSearchResults";

    private RecyclerView mRecyclerView;
    private AdvancedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> saved_recipes_ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_results);
        Log.d(TAG, "onCreate");

        int i =0;

        mNames = FirebaseResults.getSaved_name();
        saved_recipes_ingredients = FirebaseResults.getSaved_recipes_ingredients();
        mImageUrls = FirebaseResults.getSaved_imageURL();

        ArrayList<AdvancedItem> advancedList = new ArrayList<>();
        for (String object: mNames) {
            advancedList.add(new AdvancedItem(mImageUrls.get(i), mNames.get(i), saved_recipes_ingredients.get(i)));
            i++;
        }

        mRecyclerView = findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdvancedAdapter(advancedList , NameSearchResults.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
