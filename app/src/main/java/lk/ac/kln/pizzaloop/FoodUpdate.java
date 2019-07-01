package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodUpdate extends AppCompatActivity {

    public EditText eTQtyUp;
    public String pizName1;
    public Float price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_update);


        Intent intent1 = getIntent();

        TextView name1 = (TextView) findViewById(R.id.txtNameUp);
        TextView price = (TextView) findViewById(R.id.txtPriceVUp);

      //  btnAddCart = (ImageButton) findViewById(R.id.btnAddCart);
      //  imgBtnGoCart1 = (ImageButton) findViewById(R.id.imgBtnGoCart1);
        eTQtyUp = (EditText) findViewById(R.id.eTQtyUp);

      //  name1.setText(intent.getStringExtra("pizName"));
        pizName1= intent1.getStringExtra("pizName");
        name1.setText(pizName1);

        price1=intent1.getFloatExtra("cPrice",0);
    }
}
