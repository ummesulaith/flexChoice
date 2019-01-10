package flexchoice.com.flexchoice.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import flexchoice.com.flexchoice.Fragments.ItemdetailFragment;
import flexchoice.com.flexchoice.R;

import static android.support.constraint.Constraints.TAG;

public class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.MyViewHolder> {



private ArrayList<String> mData = new ArrayList<>();
private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;

    public RecyclerGridViewAdapter(Context mContext,ArrayList<String> mData, ArrayList<String> mImage) {
        this.mData = mData;
        this.mImage = mImage;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view= mInflater.inflate(R.layout.single_item,parent,false);
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.img_furniture_thumbnail);

        holder.tv_furniture_title.setText(mData.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mData.get(position));

                Toast.makeText(mContext,  mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_furniture_title;
        ImageView img_furniture_thumbnail;
        CardView cardView ;

        public  MyViewHolder (View itemView){
            super(itemView);

            tv_furniture_title = (TextView) itemView.findViewById(R.id.title_furniture);
            img_furniture_thumbnail =(ImageView) itemView.findViewById(R.id.grid_item);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
