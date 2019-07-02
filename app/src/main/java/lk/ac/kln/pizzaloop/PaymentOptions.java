package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PaymentOptions extends AppCompatActivity {
    public Button btnCre;
    public Button btnCash;
    public ImageButton imgBtnGoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        getSupportActionBar().setTitle("PAYMENT");

        btnCre= (Button) findViewById(R.id.btnCre);
        btnCash= (Button) findViewById(R.id.btnCash);
        imgBtnGoCart = (ImageButton) findViewById(R.id.imgBtnGoCart);

        btnCre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditCard();
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashOn();
            }
        });

        imgBtnGoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
    }

    public void goToCart() {
        Intent intent = new Intent(PaymentOptions.this,CartActivity.class);
        startActivity(intent);
    }

    public void cashOn() {
        Intent intent = new Intent(PaymentOptions.this,CashOn.class);
        startActivity(intent);
    }

    public void creditCard() {
        Intent intent = new Intent(PaymentOptions.this,CCPay.class);
        startActivity(intent);
    }
}
