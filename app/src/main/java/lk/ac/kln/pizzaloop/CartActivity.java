package lk.ac.kln.pizzaloop;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CartActivity extends AppCompatActivity {

   private List<Cart> cartDetails = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.8.112:8080/demo/cartall"; //192.168.8.112 172.19.31.144

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(request);
    }

    class HTTPResponseListner implements Response.Listener<JSONArray>{
        @Override
        public void onResponse(JSONArray jsonArray) {
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object1= jsonArray.getJSONObject(i);
                    Cart cart = new Cart();
                    cart.setQty(Integer.parseInt(object1.get("qty").toString()));
                    cart.setPizName(object1.get("pizName").toString());
                    cart.setPrice(Float.parseFloat(object1.get("price").toString()));
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

        CustomAdapter(Context context, int resource, List<Cart> items){
            super(context,resource,items);
            itemsList = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.cart_load,parent,false);

            }

            Cart item = itemsList.get(position);
            TextView tv =convertView.findViewById(R.id.txtCartFoodN);
            TextView tv1 =convertView.findViewById(R.id.txtCartP1);
            TextView tv2 =convertView.findViewById(R.id.txtCartQty);

            tv.setText("Name:  "+item.getPizName());
            tv1.setText("Price: Rs."+item.getPrice());
            tv2.setText("Quantity:"+item.getQty());
            return  convertView;
        }
    }
}
