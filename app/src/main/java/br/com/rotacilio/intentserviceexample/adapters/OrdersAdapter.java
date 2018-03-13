package br.com.rotacilio.intentserviceexample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rotacilio.intentserviceexample.R;
import br.com.rotacilio.intentserviceexample.adapters.holders.OrderViewHolder;
import br.com.rotacilio.intentserviceexample.retrofit.models.OrderItem;

/**
 * Created by roqls on 12/03/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<OrderItem> orders;

    public OrdersAdapter(List<OrderItem> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bindView(this.orders.get(position));
    }

    @Override
    public int getItemCount() {
        return this.orders.size();
    }
}
