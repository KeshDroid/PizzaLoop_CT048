package lk.ac.kln.pizzaloop;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.ClientError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class CartActivity extends AppCompatActivity {

    ImageButton imgBtnPurchase;
    ImageButton imgBtnDel;

   private List<Cart> cartDetails = new ArrayList<>();
   ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("CART");

       // imgBtnDel=(ImageButton) findViewById(R.id.imgBtnDel);
        listView = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.activity_list_item);
       // imgBtnDel.setAdapter

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object ob = listView.getItemAtPosition(position);
                Cart cart = (Cart) ob;

                Intent intent = new Intent(view.getContext(), FoodUpdate.class);
                intent.putExtra("pizName", cart.getPizName());
                intent.putExtra("cPrice", cart.getcPrice());
                intent.putExtra("qty", cart.getQty());
                intent.putExtra("total", cart.getTotal());
                startActivityForResult(intent, 0);
                System.out.println("fsgsg");
            }
        });



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.8.112:8080/demo/cartall"; //192.168.8.112 172.19.31.144

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(request);


        imgBtnPurchase=(ImageButton) findViewById(R.id.imgBtnPurchase);
       // imgBtnDel=(ImageButton) findViewById(R.id.imgBtnDel);
        imgBtnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPurchase();
            }
        });
        //getApplicationContext(), R.layout.cart_load;

   /*   imgBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPurchase();

            }
        });*/
    }



    public void goToPurchase(){
        Intent intent = new Intent(CartActivity.this,PaymentOptions.class);
        startActivity(intent);
    }

    class HTTPResponseListner implements Response.Listener<JSONArray>{
        @Override
        public void onResponse(JSONArray jsonArray) {
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object1= jsonArray.getJSONObject(i);
                    Cart cart = new Cart();
                    cart.setId(Integer.parseInt(object1.get("id").toString()));
                    cart.setQty(Integer.parseInt(object1.get("qty").toString()));
                    cart.setPizName(object1.get("pizName").toString());
                    cart.setcPrice(Float.parseFloat(object1.get("cPrice").toString()));
                    cart.setTotal(Float.parseFloat(object1.get("total").toString()));
                    // pizza.setImageURL(object.get("imageUrl").toString());
                    cartDetails.add(cart);
                    System.out.println(cart);

                    ListView cartList = findViewById(R.id.listView1);
                    CustomAdapter listAdapter = new CustomAdapter(getApplicationContext(), R.layout.cart_load, cartDetails);
                    cartList.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    }

    private class CustomAdapter extends ArrayAdapter<Cart>{
        private  List<Cart> itemsList;
        private int layout;

        CustomAdapter(Context context, int resource, List<Cart> items){
            super(context,resource,items);
            itemsList = items;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.cart_load,parent,false);
               // ViewHolder viewHolder= new ViewHolder();
                //viewHolder.imgBtnDel= (ImageButton) convertView.findViewById(R.id.imgBtnDel);
              //  viewHolder.imgBtnAdd= (ImageButton) convertView.findViewById(R.id.imgBtnAdd);
               // viewHolder.imgBtnList= (ImageButton) convertView.findViewById(R.id.imgBtnList);
              /*  viewHolder.imgBtnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"111" ,Toast.LENGTH_SHORT).show();
                    }
                });*

                viewHolder.imgBtnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"100" ,Toast.LENGTH_SHORT).show();


                    }
                });

                viewHolder.imgBtnList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Food Items" ,Toast.LENGTH_SHORT).show();
                        goToList();
                    }
                });*/
            }

            Cart item = itemsList.get(position);
            TextView tv =convertView.findViewById(R.id.txtCartFoodN);
            TextView tv1 =convertView.findViewById(R.id.txtCartP1);
            TextView tv2 =convertView.findViewById(R.id.txtCartQty);

            tv.setText(""+item.getPizName());
            tv1.setText("Rs."+item.getTotal());
            tv2.setText(""+item.getQty());
            return  convertView;
        }
    }

  /*  public void goToList() {
        Intent intent = new Intent(CartActivity.this,FoodItems.class);
        startActivity(intent);
    }*/

  /*  public class ViewHolder{
        ImageButton imgBtnDel;
        ImageButton imgBtnAdd;
        ImageButton imgBtnList;
    }*/


}
