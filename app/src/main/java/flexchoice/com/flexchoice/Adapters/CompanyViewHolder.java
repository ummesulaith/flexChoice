package flexchoice.com.flexchoice.Adapters;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import flexchoice.com.flexchoice.Activity.Company;
import flexchoice.com.flexchoice.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class CompanyViewHolder extends GroupViewHolder {

    private TextView mCo;
    private ImageView arrow;


    public CompanyViewHolder(View itemView) {
        super(itemView);

        mCo = (TextView)itemView.findViewById(R.id.co);
        arrow =(ImageView)itemView.findViewById(R.id.arrow);

    }
    public void bind(Company company)
    {
        mCo.setText(company.getTitle());
    }



    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}

