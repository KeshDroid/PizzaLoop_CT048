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

public class FoodItems extends AppCompatActivity {
    private List<Pizza> pizzaDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.8.112:8080/demo/all"; //192.168.8.112 172.19.31.144

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
                    // pizza.setImageURL(object.get("imageUrl").toString());
                    pizzaDetails.add(pizza);
                    System.out.println(pizza);

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
            // ImageView iv = convertView.findViewById(R.id.imageView);
            // Picasso.get().load(item.getImageURL()).into(iv);
            tv.setText(item.getName());
            return convertView;
        }
    }
}
