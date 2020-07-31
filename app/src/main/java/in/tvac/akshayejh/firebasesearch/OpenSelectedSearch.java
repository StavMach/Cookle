package in.tvac.akshayejh.firebasesearch;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OpenSelectedSearch extends AppCompatActivity {
    private static final String TAG = "OpenSelectedSearch";


    public TextView mRecName;
    public TextView mRecExec;
    public TextView mRecIngr;
    public ImageView mRecImg;


    private Integer mIndex;
    private String recip_exec;
    private String recip_ingr;
    private String imageURL;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mExecution = new ArrayList<>();
    private ArrayList<String> saved_recipes_ingredients = new ArrayList<>();
    private ArrayList<String> imageURLList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_advanced);
        Log.d(TAG, "onCreate");

        Bundle bundle = getIntent().getExtras();
        String recipName = bundle.getString("recipName");
        mNames = FirebaseResults.getSaved_name();
        saved_recipes_ingredients = FirebaseResults.getSaved_recipes_ingredients();
        imageURLList = FirebaseResults.getSaved_imageURL();
        mExecution = FirebaseResults.getSaved_execution();



        mIndex = mNames.indexOf(recipName);
        recip_exec =  mExecution.get(mIndex);
        recip_ingr =  saved_recipes_ingredients.get(mIndex);
        imageURL = imageURLList.get(mIndex);


        mRecImg = findViewById(R.id.mImageADV);
        Picasso.get().load(imageURL).into(mRecImg);
        mRecName =  findViewById(R.id.RecNameADV);
        mRecName.setText(recipName);
        mRecExec =  findViewById(R.id.recExecutionADV);
        mRecExec.setText(recip_exec);
        mRecIngr =  findViewById(R.id.recIngredientsADV);
        mRecIngr.setText(recip_ingr);

    }


}