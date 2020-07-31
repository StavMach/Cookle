package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CookleMain extends AppCompatActivity {
    private static final String TAG = "Cookle_main";

    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private ImageButton mAdvSearchBtn;
    private static CookleMain INSTANCE;
    private static  String data;
    private static String searchRecipe;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private AdvancedQuery advQuer;
    private static ArrayList<String> database_imageURL= new ArrayList<>();
    private static ArrayList<String> database_name= new ArrayList<>();
    private static ArrayList<String> database_ingredient= new ArrayList<>();
    private static ArrayList<Integer> database_total_num= new ArrayList<>();
    private static ArrayList<String> database_execution= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookle_main);
        Log.d(TAG, "onCreate");

        INSTANCE=this;
        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mAdvSearchBtn= (ImageButton) findViewById(R.id.advsearch_btn);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Recipes");
        goListener();
    }
    public static CookleMain getActivityInstance()
    {
        return INSTANCE;
    }


    private void goListener() {
        Log.d(TAG, "goListener");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    advQuer = recipeSnapshot.getValue(AdvancedQuery.class);
                    if (advQuer.getIngredients() != null) {
                        database_ingredient.add(advQuer.getIngredients());
                    }
                    if (advQuer.getName() != null) {
                        database_name.add(advQuer.getName());
                    }
                    if (advQuer.getTotalNum() != null) {
                        database_total_num.add(Integer.parseInt(advQuer.getTotalNum()));
                    }
                    if (advQuer.getImage() != null) {
                        database_imageURL.add(advQuer.getImage());
                    }
                    if (advQuer.getExecution() != null) {
                        database_execution.add(advQuer.getExecution());
                    }
                }
                Log.d(TAG, database_ingredient.toString());
                Log.d(TAG, database_name.toString());
                Log.d(TAG, database_total_num.toString());
                Log.d(TAG, database_execution.toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public String getData()
    {
        Log.d(TAG, "getData");

        return this.searchRecipe;

    }
    //i call this function in the recipe history
//    public void nullHistory() { searchRecipe = ""; }

  //recipe search--Starts search
    public void searchBTN(View view)
    {
        Log.d(TAG, "searchBTN");

        searchRecipe = mSearchField.getText().toString();
        data = searchRecipe;
        if(!searchRecipe.equals("")) {
            Intent intentLoadNewActivity = new Intent(CookleMain.this, FirebaseResults.class);
            intentLoadNewActivity.putExtra("names", searchRecipe);
            startActivity(intentLoadNewActivity);
        }

    }

    public void advSearchBTN(View view)
    {
        Log.d(TAG, "ADVsearchBTN");
        Intent intentLoadNewActivity = new Intent(CookleMain.this, AdvancedSearch.class);
        startActivity(intentLoadNewActivity);

    }



    public static ArrayList<String> getDatabase_imageURL() {
        return database_imageURL;
    }

    public void setDatabase_imageURL(ArrayList<String> database_imageURL) {
        this.database_imageURL = database_imageURL;
    }

    public static ArrayList<String> getDatabase_name() {
        return database_name;
    }

    public void setDatabase_name(ArrayList<String> database_name) {
        this.database_name = database_name;
    }

    public static ArrayList<String> getDatabase_ingredient() {
        return database_ingredient;
    }

    public void setDatabase_ingredient(ArrayList<String> database_ingredient) {
        this.database_ingredient = database_ingredient;
    }

    public static ArrayList<Integer> getDatabase_total_num() {
        return database_total_num;
    }

    public void setDatabase_total_num(ArrayList<Integer> database_total_num) {
        this.database_total_num = database_total_num;
    }

    public static ArrayList<String> getDatabase_execution() {
        return database_execution;
    }

    public void setDatabase_execution(ArrayList<String> database_execution) {
        this.database_execution = database_execution;
    }
}
