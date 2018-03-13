package br.com.rotacilio.intentserviceexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.intentserviceexample.adapters.OrdersAdapter;
import br.com.rotacilio.intentserviceexample.models.Order;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ordersList = (RecyclerView) findViewById(R.id.ordersList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.ordersList.setLayoutManager(layoutManager);

        OrdersAdapter adapter = new OrdersAdapter(this.getOrders());
        this.ordersList.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this.ordersList.getContext(), layoutManager.getOrientation());
        this.ordersList.addItemDecoration(dividerItemDecoration);
    }

    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        Order order1 = new Order("Pedido 1", "Descrição do pedido 1", "52,99");
        Order order2 = new Order("Pedido 2", "Descrição do pedido 2", "50,99");
        Order order3 = new Order("Pedido 3", "Descrição do pedido 3", "45,99");
        Order order4 = new Order("Pedido 4", "Descrição do pedido 4", "32,99");
        Order order5 = new Order("Pedido 5", "Descrição do pedido 5", "21,54");
        Order order6 = new Order("Pedido 6", "Descrição do pedido 6", "15,99");
        Order order7 = new Order("Pedido 7", "Descrição do pedido 7", "88,99");
        Order order8 = new Order("Pedido 8", "Descrição do pedido 8", "39,99");
        Order order9 = new Order("Pedido 9", "Descrição do pedido 9", "46,99");
        Order order10 = new Order("Pedido 10", "Descrição do pedido 10", "8,99");

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);
        orders.add(order10);

        return orders;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSyncList:
                Toast.makeText(this, "Atualizar Lista", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
