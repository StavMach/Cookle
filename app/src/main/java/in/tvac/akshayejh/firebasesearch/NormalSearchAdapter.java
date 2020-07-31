package in.tvac.akshayejh.firebasesearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NormalSearchAdapter extends RecyclerView.Adapter<NormalSearchAdapter.NormalSearchAdapterViewHolder> {
    private ArrayList<AdvancedItem> mAdvancedList;
    private Context context;
    public String mImage;


    public static class NormalSearchAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mImagebtn;
        public TextView mTextView1;
        public RelativeLayout madv_layout;

        public NormalSearchAdapterViewHolder(View itemView) {
            super(itemView);
            mImagebtn = itemView.findViewById(R.id.imageButtonADV);
            mTextView1 = itemView.findViewById(R.id.textViewADV1);
            madv_layout = itemView.findViewById(R.id.adv_layout);
        }

    }

    public NormalSearchAdapter(ArrayList<AdvancedItem> advancedList,Context context) {

        this.context = context;
        mAdvancedList = advancedList;
    }

    @Override
    public NormalSearchAdapter.NormalSearchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advanced_item, parent, false);
        NormalSearchAdapter.NormalSearchAdapterViewHolder evh = new NormalSearchAdapter.NormalSearchAdapterViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final NormalSearchAdapter.NormalSearchAdapterViewHolder holder, final int position) {


        final AdvancedItem currentItem = mAdvancedList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        mImage = currentItem.getImageResource();
        Picasso.get().load(mImage).into(holder.mImagebtn);

        holder.madv_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenSelectedSearch.class);

                intent.putExtra("recipName", holder.mTextView1.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdvancedList.size();
    }
}