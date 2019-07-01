package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentOptions extends AppCompatActivity {
    public Button btnCre;
    public Button btnCash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        btnCre= (Button) findViewById(R.id.btnCre);
        btnCash= (Button) findViewById(R.id.btnCash);

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
