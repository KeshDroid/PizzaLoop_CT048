package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu extends AppCompatActivity {
   // private Button btn;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Pizza> pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);


        recyclerView =  (RecyclerView) findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pizza= new ArrayList<>();


        for (int i = 0; i<=10; i++){
            Pizza pizza2 = new Pizza("name" + i+1,"dsfggs","hdjjdk");

            pizza.add(pizza2);
        }
        adapter = new Adapter(pizza,this);

        recyclerView.setAdapter(adapter);


       // btn = (Button) findViewById(R.id.btn);
       // btnSignIn = (Button) findViewById(R.id.btnSignIn);

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });*/


    }
   /* public void openFood() {
        Intent intent = new Intent(FoodMenu.this, Food.class);
        startActivity(intent);
    }*/

}
