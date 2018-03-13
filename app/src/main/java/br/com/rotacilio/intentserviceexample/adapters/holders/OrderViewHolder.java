package br.com.rotacilio.intentserviceexample.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.rotacilio.intentserviceexample.R;
import br.com.rotacilio.intentserviceexample.retrofit.models.OrderItem;

/**
 * Created by roqls on 12/03/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private TextView description;
    private TextView value;
    private View itemView;

    public OrderViewHolder(View itemView) {
        super(itemView);

        this.itemView = itemView;
        this.title = (TextView) itemView.findViewById(R.id.orderTitle);
        this.description = (TextView) itemView.findViewById(R.id.orderDescription);
        this.value = (TextView) itemView.findViewById(R.id.orderValue);
        this.itemView.setOnClickListener(this);
    }

    public void bindView(OrderItem order) {
        this.title.setText(order.getTitle());
        this.description.setText(order.getDescription());
        this.value.setText(order.getValue());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),
                "Nome: " + this.title.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
