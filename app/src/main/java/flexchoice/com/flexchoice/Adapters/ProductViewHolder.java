package flexchoice.com.flexchoice.Adapters;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import flexchoice.com.flexchoice.Activity.Product;
import flexchoice.com.flexchoice.R;

public class ProductViewHolder extends ChildViewHolder {

     TextView ptxt;
    public ProductViewHolder(View itemView) {
        super(itemView);
        ptxt = (TextView)itemView.findViewById(R.id.prd);

    }

    public void  bind(Product product)
    {
        ptxt.setText(product.name);
    }
}
