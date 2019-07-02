package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CCPay extends AppCompatActivity {

    public ImageButton imgBtnGoCart;
    public Button btnCon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccpay);
        getSupportActionBar().setTitle("PAYMENT");

        imgBtnGoCart= (ImageButton) findViewById(R.id.imgBtnGoCart);
        btnCon1= (Button) findViewById(R.id.btnCon1);

        imgBtnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });

        btnCon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CCPay.this, "Set Your Location", Toast.LENGTH_LONG).show();
                goToMap();
            }
        });
    }

    private void goToMap() {
        Intent intent = new Intent(CCPay.this,MapsActivity.class);
        startActivity(intent);
    }

    private void goToCart() {
        Intent intent = new Intent(CCPay.this,FoodItems.class);
        startActivity(intent);
    }
}
