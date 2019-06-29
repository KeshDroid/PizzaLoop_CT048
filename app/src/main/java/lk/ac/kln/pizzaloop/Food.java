package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.txtName);
        TextView description = (TextView) findViewById(R.id.txtIngDe);
       //TextView price = (TextView) findViewById(R.id.txtPriceV);
        ImageView imageView =(ImageView) findViewById(R.id.imgFood);

        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        //price.setText(intent.getStringExtra("price"));
        Picasso.get().load(intent.getStringExtra("imgurl")).into(imageView);
    }
}
