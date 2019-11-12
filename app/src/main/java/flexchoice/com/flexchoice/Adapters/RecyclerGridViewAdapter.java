package flexchoice.com.flexchoice.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import flexchoice.com.flexchoice.R;


public class RecyclerGridViewAdapter extends RecyclerView.ViewHolder  {


    public   TextView tv_furniture_title,td,tp;
    public   ImageView img_furniture_thumbnail;
    public CardView cardView ;
    private ArrayList<String> mData = new ArrayList<>();
    private ArrayList<String> mPrice = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;

    public RecyclerGridViewAdapter(View itemView) {
        super(itemView);
        tv_furniture_title = (TextView) itemView.findViewById(R.id.title_furniture);
        td = (TextView)itemView.findViewById(R.id.tdec);
        tp = (TextView)itemView.findViewById(R.id.tprice);
        img_furniture_thumbnail =(ImageView) itemView.findViewById(R.id.grid_item);
        cardView = (CardView) itemView.findViewById(R.id.cardview_id);
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder{

           TextView tv_furniture_title,td,tp;
           ImageView img_furniture_thumbnail;
         CardView cardView ;

        public  MyViewHolder (View itemView)
        {
            super(itemView);

            tv_furniture_title = (TextView) itemView.findViewById(R.id.title_furniture);
            td = (TextView)itemView.findViewById(R.id.tdec);
            tp = (TextView)itemView.findViewById(R.id.tprice);
            img_furniture_thumbnail =(ImageView) itemView.findViewById(R.id.grid_item);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}