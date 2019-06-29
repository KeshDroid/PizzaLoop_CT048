package lk.ac.kln.pizzaloop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodItems extends AppCompatActivity {
    private List<Pizza> pizzaDetails = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);
        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.activity_list_item);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>   parent, View view, int position, long id) {
                Object ob = listView.getItemAtPosition(position);
                Pizza pizza = (Pizza) ob;
                Intent intent = new Intent(view.getContext(),Food.class);
                intent.putExtra("name",pizza.getName());
                intent.putExtra("description",pizza.getDescription());
                intent.putExtra("imgurl",pizza.getImgurl());
                //intent.putExtra("price",pizza.getPrice());
                startActivityForResult(intent, 0);
               // startActivityForResult(intent, );


            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.43.55:8080/demo/all"; //192.168.8.112 172.19.31.144  192.168.43.55

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(request);
    }

    class HTTPResponseListner implements Response.Listener<JSONArray>{
        @Override
        public void onResponse(JSONArray jsonArray) {
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object= jsonArray.getJSONObject(i);
                    Pizza pizza = new Pizza();
                    pizza.setPizzaId(Integer.parseInt(object.get("pizzaId").toString()));
                    pizza.setName(object.get("name").toString());
                    //pizza.setPrice(Float.parseFloat(object.get("price").toString()));
                    pizza.setDescription(object.get("description").toString());
                    pizza.setImgurl(object.get("imgurl").toString());
                    pizzaDetails.add(pizza);
                   // System.out.println(pizza);

                    ListView pizzaList = findViewById(R.id.listView);
                    CustomAdapter listAdapter = new CustomAdapter(getApplicationContext(), R.layout.food_items, pizzaDetails);
                    pizzaList.setAdapter(listAdapter);


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

    private class CustomAdapter extends ArrayAdapter<Pizza> {
        private List<Pizza> itemsList;

        CustomAdapter(Context context, int resource, List<Pizza> items) {
            super(context, resource, items);
            itemsList = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().from(getContext()).inflate(R.layout.food_items, parent, false);
            }
            Pizza item = itemsList.get(position);
            TextView tv =  convertView.findViewById(R.id.txtFoodN);
            TextView tv1 =  convertView.findViewById(R.id.txtP1);
            ImageView iv = convertView.findViewById(R.id.imgFood);
            Picasso.get().load(item.getImgurl()).into(iv);
            tv.setText(item.getName());
            tv1.setText(item.getDescription());
            //tv1.setText(item.getPrice());
            //iv.setImage(item.getImgurl());
            return convertView;
        }
    }
}
