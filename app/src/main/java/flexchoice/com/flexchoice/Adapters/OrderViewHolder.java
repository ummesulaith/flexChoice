package flexchoice.com.flexchoice.Adapters;

import android.view.View;
import android.widget.TextView;

import flexchoice.com.flexchoice.R;

public class OrderViewHolder extends RecyclerGridViewAdapter.MyViewHolder implements View.OnClickListener{

    public TextView prodname,prodstatus,prodprice;

    public ItemClickListner listner;

    public OrderViewHolder(View itemView) {
        super(itemView);

      //  prodname = (TextView)itemView.findViewById(R.id.ordername);
//        prodstatus = (TextView)itemView.findViewById(R.id.orderstatus);
      //  prodprice = (TextView)itemView.findViewById(R.id.orderprice);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view,getAdapterPosition(),false);

    }
}
