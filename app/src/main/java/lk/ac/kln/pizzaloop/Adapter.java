package lk.ac.kln.pizzaloop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Pizza> pizza;
    private Context context;

    public Adapter(List <Pizza> pizza, Context context) {
        this.pizza = pizza;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {

        Pizza pi = pizza.get(position );

        viewHolder.txtName1.setText(pi.getName());
        viewHolder.txtDes1.setText(pi.getDescription());


    }

    @Override
    public int getItemCount() {
        return pizza.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName1;
        public TextView txtDes1;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName1 = (TextView) itemView.findViewById(R.id.txtName1);
            txtDes1 = (TextView) itemView.findViewById(R.id.txtDes1);

        }
    }
}
