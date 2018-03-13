package br.com.rotacilio.intentserviceexample;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import br.com.rotacilio.intentserviceexample.adapters.OrdersAdapter;
import br.com.rotacilio.intentserviceexample.retrofit.RetrofitConfig;
import br.com.rotacilio.intentserviceexample.retrofit.models.Order;
import br.com.rotacilio.intentserviceexample.retrofit.models.OrderItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Order> {

    private List<OrderItem> openedOrders;
    private RecyclerView ordersList;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ordersList = (RecyclerView) findViewById(R.id.ordersList);

        this.initDialog();
        this.fetchOrders();
    }

    private void configureRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.ordersList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this.ordersList.getContext(), layoutManager.getOrientation());
        this.ordersList.addItemDecoration(dividerItemDecoration);

        OrdersAdapter adapter = new OrdersAdapter(this.openedOrders);
        this.ordersList.setAdapter(adapter);
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
                this.initDialog();
                this.fetchOrders();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDialog() {
        this.dialog = new ProgressDialog(this);
        this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.dialog.setMessage(getString(R.string.message_progress_dialog_orders));
        this.dialog.setCancelable(false);
        this.dialog.setIndeterminate(true);
        this.dialog.show();
    }

    private void dismissDialog() {
        this.dialog.dismiss();
    }

    private void fetchOrders() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<Order> call = new RetrofitConfig().getOrdersService().getOpenedOrders();
                call.enqueue(MainActivity.this);
            }
        }, 2000);
    }

    @Override
    public void onResponse(Call<Order> call, Response<Order> response) {
        Order order = response.body();
        openedOrders = order.getOrders();
        configureRecyclerView();
        this.dismissDialog();
    }

    @Override
    public void onFailure(Call<Order> call, Throwable t) {
        Log.e("RETROFIT_ERROR", t.getMessage());
        this.dismissDialog();
    }
}