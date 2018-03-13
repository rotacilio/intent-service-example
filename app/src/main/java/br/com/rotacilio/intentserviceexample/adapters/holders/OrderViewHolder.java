package br.com.rotacilio.intentserviceexample.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rotacilio.intentserviceexample.R;
import br.com.rotacilio.intentserviceexample.models.Order;

/**
 * Created by roqls on 12/03/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView value;

    public OrderViewHolder(View itemView) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.orderTitle);
        this.description = (TextView) itemView.findViewById(R.id.orderDescription);
        this.value = (TextView) itemView.findViewById(R.id.orderValue);
    }

    public void bindView(Order order) {
        this.title.setText(order.getTitle());
        this.description.setText(order.getDescription());
        this.value.setText(order.getValue());
    }
}
