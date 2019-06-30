package lk.ac.kln.pizzaloop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Request;

public class Food extends AppCompatActivity {

    ImageButton btnAddCart;
    public Double price,total;
    public String piz_name = "ss" ;
    public Integer qty;
   /* ImageButton btnDone;
    Dialog dialog;
    TextView txtAdded,txtSuc;
    ImageView imgClose;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.txtName);
        TextView description = (TextView) findViewById(R.id.txtIngDe);
        TextView price = (TextView) findViewById(R.id.txtPriceV);
        ImageView imageView = (ImageView) findViewById(R.id.imgFood);
        btnAddCart = (ImageButton) findViewById(R.id.btnAddCart);

        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        price.setText(intent.getStringExtra("price"));
        Picasso.get().load(intent.getStringExtra("imgurl")).into(imageView);
        System.out.println(name);


        //btnAddCart = (Button) findViewById(R.id.btnAddCart);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ShowPop();
                openNext();
            }
        });
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

        public void openNext() {


            String url = "http://192.168.8.112:8080/demo/addcart?name="+piz_name + "&price="+price  + "&total=" + total + "&qty="+ qty;
            RequestQueue requestQueue = Volley.newRequestQueue(Food.this);

            StringRequest stringRequest =new StringRequest(
                   Request.Method.GET, url, new HTTPResponseListner(),
                   new HTTPErrorListner()
            );
            requestQueue.add(stringRequest);
            System.out.println(stringRequest);

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
            Toast.makeText(getApplicationContext(),"errr",Toast.LENGTH_SHORT).show();
        }
    }

    }


