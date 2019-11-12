package flexchoice.com.flexchoice.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import flexchoice.com.flexchoice.R;

public class GridAdapter extends BaseAdapter {

     Context context;
    View view;
    LayoutInflater layoutInflater;
    private final Integer[] mThumbIds;


    public GridAdapter(Context context, Integer[] mThumbIds)
    {
        this.context=context;
        this.mThumbIds = mThumbIds;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
        {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.grid_item);
            imageView.setImageResource(mThumbIds[position]);
        }

        return view;
    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.f1, R.drawable.f2,
//            R.drawable.f3, R.drawable.f4
//
//    };
}

//  layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//          view = new View(mContext);
//            view = layoutInflater.inflate(R.layout.single_item, null);