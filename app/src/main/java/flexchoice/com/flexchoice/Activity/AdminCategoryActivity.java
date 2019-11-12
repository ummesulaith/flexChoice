package flexchoice.com.flexchoice.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import flexchoice.com.flexchoice.R;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView chair, table, dressingtable, chest,bed,wadrobe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        chair = (ImageView)findViewById(R.id.chair);

        table = (ImageView)findViewById(R.id.tables);


        dressingtable = (ImageView)findViewById(R.id.dressing_table);

        chest = (ImageView)findViewById(R.id.chest);

        bed = (ImageView)findViewById(R.id.bed);

        wadrobe = (ImageView)findViewById(R.id.wadrobe);

        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                i.putExtra("category", "chair");
                startActivity(i);
            }
        });
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                in.putExtra("category", "table");
                startActivity(in);
            }
        });

        dressingtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                in.putExtra("category", "dressing table");
                startActivity(in);
            }
        });

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                in.putExtra("category", "chest");
                startActivity(in);
            }
        });

        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                in.putExtra("category", "bed");
                startActivity(in);
            }
        });

        wadrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                in.putExtra("category", "wadrobe");
                startActivity(in);
            }
        });


    }
}
