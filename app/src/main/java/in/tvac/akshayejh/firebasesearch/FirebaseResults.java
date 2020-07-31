package in.tvac.akshayejh.firebasesearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;

public class FirebaseResults extends AppCompatActivity {
    private static final String TAG = "FirebaseResults";
    private static ArrayList<String> saved_imageURL;
    private static ArrayList<String> saved_name;
    private static ArrayList<String> saved_recipes_ingredients;
    private static ArrayList<String> saved_execution;
    private ArrayList<String> database_imageURL = new ArrayList<>();
    private ArrayList<String> database_name = new ArrayList<>();
    private ArrayList<String> database_ingredient = new ArrayList<>();
    private ArrayList<String> database_execution = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        database_ingredient = new ArrayList<>();
        database_name = new ArrayList<>();
        database_imageURL = new ArrayList<>();
        database_execution = new ArrayList<>();
        saved_imageURL = new ArrayList<>();
        saved_name = new ArrayList<>();
        saved_recipes_ingredients = new ArrayList<>();
        saved_execution = new ArrayList<>();
        database_imageURL = CookleMain.getDatabase_imageURL();
        database_name = CookleMain.getDatabase_name();
        database_ingredient = CookleMain.getDatabase_ingredient();
        database_execution = CookleMain.getDatabase_execution();
        Intent intent = getIntent();
        final String receivedData = intent.getStringExtra("names");
        Log.d("TEST", receivedData.toString());
        int temp;
        Log.d("TEST", "ee");
        for (String s : database_name) {
            if (s.toLowerCase().contains(receivedData.toLowerCase())){
                temp = database_name.indexOf(s);
                saved_recipes_ingredients.add(database_ingredient.get(temp));
                saved_name.add(database_name.get(temp));
                saved_imageURL.add(database_imageURL.get(temp));
                saved_execution.add(database_execution.get(temp));
            }
        }
        Intent intentResults = new Intent(FirebaseResults.this, OpenNameResults.class);
        startActivity(intentResults);
    }

    public static ArrayList<String> getSaved_imageURL() {
        return saved_imageURL;
    }

    public static void setSaved_imageURL(ArrayList<String> saved_imageURL) {
        FirebaseResults.saved_imageURL = saved_imageURL;
    }

    public static ArrayList<String> getSaved_name() {
        return saved_name;
    }

    public static void setSaved_name(ArrayList<String> saved_name) {
        FirebaseResults.saved_name = saved_name;
    }

    public static ArrayList<String> getSaved_recipes_ingredients() {
        return saved_recipes_ingredients;
    }

    public static void setSaved_recipes_ingredients(ArrayList<String> saved_recipes_ingredients) {
        FirebaseResults.saved_recipes_ingredients = saved_recipes_ingredients;
    }

    public static ArrayList<String> getSaved_execution() {
        return saved_execution;
    }

    public static void setSaved_execution(ArrayList<String> saved_execution) {
        FirebaseResults.saved_execution = saved_execution;
    }
}



