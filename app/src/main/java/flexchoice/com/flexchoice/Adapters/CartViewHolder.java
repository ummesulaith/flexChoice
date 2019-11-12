package flexchoice.com.flexchoice.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import flexchoice.com.flexchoice.R;

public class CartViewHolder  extends RecyclerGridViewAdapter.MyViewHolder implements View.OnClickListener{

  public   TextView txtProductname,txtProductprice,removeitem;
    public ItemClickListner listner;
    public ImageView cartimg;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtProductname = itemView.findViewById(R.id.txtpname);
        txtProductprice = itemView.findViewById(R.id.txtprice);
//        cartimg = itemView.findViewById(R.id.imagecartlist);
        removeitem = itemView.findViewById(R.id.removecart);
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
