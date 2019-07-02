package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class FoodUpdate extends AppCompatActivity {


    public String pizName1;
    public Float price1,qty1,total;
    public Integer qtyUp;
    public String qty;
    public ImageButton imgBtnDelete,btnUpdateCart;
    public ImageButton imgBtnGoCartUp;
    ElegantNumberButton elegantNumberButton1;
    private ElegantNumberButton Qunty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_update);
        getSupportActionBar().setTitle("UPDATE ORDER");


        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.txtNameUp);
        TextView price = (TextView) findViewById(R.id.txtPriceVUp);

        btnUpdateCart = (ImageButton) findViewById(R.id.btnUpdateCart);
        imgBtnGoCartUp = (ImageButton) findViewById(R.id.imgBtnGoCartUp);
        imgBtnDelete = (ImageButton) findViewById(R.id.imgBtnDelete);
        elegantNumberButton1= findViewById(R.id.ele2);



        pizName1= intent.getStringExtra("pizName");
        name.setText(pizName1);
        System.out.println(pizName1);

        price1=intent.getFloatExtra("cPrice",0);
        price.setText("Rs. "+price1);
        System.out.println(price1);

        //qty = intent.get




       elegantNumberButton1.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) { //To edit quantity
                qty= elegantNumberButton1.getNumber();
                qtyUp=Integer.parseInt(qty);
                qty1= Float.parseFloat(qty);
                total= qty1*price1;
                System.out.println(total);
            }
        });

        imgBtnGoCartUp.setOnClickListener(new View.OnClickListener() { // Go to FOOD ITEMS
            @Override
            public void onClick(View v) {
                goPurchace();
            }
        });

        btnUpdateCart.setOnClickListener(new View.OnClickListener() { //Update Cart
            @Override
            public void onClick(View v) {
                updateCartOne();
                goCart();
               // Toast.makeText(FoodUpdate.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        imgBtnDelete.setOnClickListener(new View.OnClickListener() { //Delete Item
            @Override
            public void onClick(View v) {
                deleteByPizName();
                goCart();
            }
        });

    }

    public void goPurchace() {
        Intent intent = new Intent(FoodUpdate.this,PaymentOptions.class);
        startActivity(intent);
    }


    public void goCart() {
        Intent intent = new Intent(FoodUpdate.this,CartActivity.class);
        startActivity(intent);
    }
    public void updateCartOne(){
        String url = "http://192.168.8.112:8080/demo/updatecart?pizName="+pizName1 + "&cPrice="+price1  + "&qty="+ qtyUp + "&total=" +total;
        RequestQueue requestQueue = Volley.newRequestQueue(FoodUpdate.this);
        StringRequest stringRequest =new StringRequest(
                Request.Method.GET, url, new HTTPResponseListner(),
                new HTTPErrorListner()
        );
        requestQueue.add(stringRequest);
        //  startActivity(new Intent(Food.this,FoodItems.class));
        System.out.println(stringRequest);
    }


    public void deleteByPizName(){
        String url = "http://192.168.8.112:8080/demo/deleteByPizName?pizName="+pizName1;
        RequestQueue requestQueue = Volley.newRequestQueue(FoodUpdate.this);
        StringRequest stringRequest =new StringRequest(
                Request.Method.GET, url, new HTTPResponseListner(),
                new HTTPErrorListner()
        );
        requestQueue.add(stringRequest);
        //  startActivity(new Intent(Food.this,FoodItems.class));
        System.out.println(stringRequest);
    }

    private    class HTTPResponseListner implements Response.Listener<String>{
        public void onResponse(String response){

            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();

        }
    }

    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"errr"+error,Toast.LENGTH_SHORT).show();
        }
    }
}
