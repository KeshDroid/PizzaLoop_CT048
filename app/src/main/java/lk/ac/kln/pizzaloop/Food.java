package lk.ac.kln.pizzaloop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Request;

public class Food extends AppCompatActivity {

    ImageButton btnAddCart;
    private Float price1,total,fprice,finTot;
    private String pizName;
    public  Integer qty , fqty;
    public String qty1;
    public EditText eTQty;
    private String  des;
    public int ff;

    ElegantNumberButton elegantNumberButton;
    private ElegantNumberButton Qunty;
   // private Button btnAddCart;
   /* ImageButton btnDone;
    Dialog dialog;
    TextView txtAdded,txtSuc;
    ImageView imgClose;*/
    public ImageButton imgBtnGoCart1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Qunty=(ElegantNumberButton) findViewById(R.id.ele1);
        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.txtName);
        TextView description = (TextView) findViewById(R.id.txtIngDe);
        TextView price = (TextView) findViewById(R.id.txtPriceV);
        ImageView imageView = (ImageView) findViewById(R.id.imgFood);
        btnAddCart = (ImageButton) findViewById(R.id.btnAddCart);
        imgBtnGoCart1 = (ImageButton) findViewById(R.id.imgBtnGoCart1);


       // name.setText(intent.getStringExtra("name"));
        pizName= intent.getStringExtra("name");
        name.setText(pizName);

        des= intent.getStringExtra("description");
        description.setText(des);

        price1=intent.getFloatExtra("price",0);
        price.setText("Rs. "+price1);

        Picasso.get().load(intent.getStringExtra("imgurl")).into(imageView);
        System.out.println(price);

        elegantNumberButton= findViewById(R.id.ele1);


        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty1= elegantNumberButton.getNumber();
                qty= Integer.parseInt(qty1);
                total= qty*price1;
                System.out.println(total);
            }
        });



        //btnAddCart = (Button) findViewById(R.id.btnAddCart);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (qty!=0){
                    addCart();
                }else{
                    Toast.makeText(Food.this, "Quantity is Empty.", Toast.LENGTH_SHORT).show();
                }
                // ShowPop();
               // openNext();
                addCart();
            }
        });

        imgBtnGoCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCart();
            }
        });




       //fprice=total;
      // fqty=qty;
    }


   /* private String finalPrice(Float price, Integer qty){

    }*/
    public void gotoCart() {
        Intent intent = new Intent(Food.this,CartActivity.class);
        startActivity(intent);
    }

    public void addCart(){
        String url = "http://192.168.8.112:8080/demo/addcart?pizName="+pizName + "&cPrice="+price1  + "&qty="+ qty + "&total=" +total;
        RequestQueue requestQueue = Volley.newRequestQueue(Food.this);
        StringRequest stringRequest =new StringRequest(
                Request.Method.GET, url, new HTTPResponseListner(),
                new HTTPErrorListner()
        );
        requestQueue.add(stringRequest);
      //  startActivity(new Intent(Food.this,FoodItems.class));
        System.out.println(stringRequest);
    }


    public void openNext() {




        Intent intent = new Intent(Food.this, FoodItems.class);
        startActivity(intent);


    }

    private    class HTTPResponseListner implements Response.Listener<String>{
        public void onResponse(String response){

            Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();

        }
    }

    private class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"errr"+error,Toast.LENGTH_SHORT).show();
        }
    }



      /* dialog = new Dialog(this);

       //btnAddCart = (ImageButton) findViewById(R.id.btnAddCart);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPop();
            }
        });


    }

    public  void ShowPop(){
        dialog.setContentView(R.layout.popup_add);
        imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        btnDone =(ImageButton) dialog.findViewById(R.id.btnDone);
        txtAdded = (TextView) dialog.findViewById(R.id.txtAdded);
        txtSuc= (TextView) dialog.findViewById(R.id.txtSuc);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openList();
            }
        });
    }
    public void openList(){
        Intent intent = new Intent(Food.this,FoodItems.class );
        startActivity(intent);
    }
    }*/

    }


