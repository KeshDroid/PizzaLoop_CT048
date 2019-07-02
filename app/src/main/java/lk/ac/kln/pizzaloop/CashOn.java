package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CashOn extends AppCompatActivity {
    public ImageButton imgBtnGoCart;
    public Button btnCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on);
        getSupportActionBar().setTitle("PAYMENT");



        imgBtnGoCart= (ImageButton) findViewById(R.id.imgBtnGoCart);
        btnCon= (Button) findViewById(R.id.btnCon);

        imgBtnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CashOn.this, "Set Your Location", Toast.LENGTH_LONG).show();
                goToMap();

            }
        });

    }

     private void goToMap() {
         Intent intent = new Intent(CashOn.this,MapsActivity.class);
         startActivity(intent);
    }

    private void goToCart() {
        Intent intent = new Intent(CashOn.this,FoodItems.class);
        startActivity(intent);
    }
}
