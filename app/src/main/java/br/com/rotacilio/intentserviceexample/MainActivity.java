package br.com.rotacilio.intentserviceexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Messenger;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.intentserviceexample.adapters.OrdersAdapter;
import br.com.rotacilio.intentserviceexample.handlers.HandleOpenedOrders;
import br.com.rotacilio.intentserviceexample.retrofit.RetrofitConfig;
import br.com.rotacilio.intentserviceexample.retrofit.models.Order;
import br.com.rotacilio.intentserviceexample.retrofit.models.OrderItem;
import br.com.rotacilio.intentserviceexample.services.OpenedOrdersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Order> {

    public static final String SERVICE_MESSAGE = "br.com.rotacilio.intentserviceexample.SERVICE_MESSAGE";

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
//        this.updateOrders();
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

    public void showResult(List<Order> obj) {
//        this.openedOrders = obj;
//        OrdersAdapter adapter = new OrdersAdapter(this.openedOrders);
//        this.ordersList.setAdapter(adapter);
//        this.dismissDialog();
    }

    public void showError(Exception obj) {
//        Toast.makeText(this, obj.getMessage(), Toast.LENGTH_SHORT).show();
//        this.dismissDialog();
    }

    private void initDialog() {
        this.dialog = new ProgressDialog(this);
        this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.dialog.setTitle(getString(R.string.title_progress_dialog_orders));
        this.dialog.setCancelable(false);
        this.dialog.setIndeterminate(true);
        this.dialog.show();
    }

    private void dismissDialog() {
        this.dialog.dismiss();
    }

//    private void updateOrders() {
//        Intent intent = new Intent(this, OpenedOrdersService.class);
//        intent.putExtra(SERVICE_MESSAGE, "Runed from Intent Service");
//        intent.putExtra(OpenedOrdersService.MESSENGER_EXTRA,
//                new Messenger(new HandleOpenedOrders(this)));
//        startService(intent);
//    }

    private void fetchOrders() {
        Call<Order> call = new RetrofitConfig().getOrdersService().getOpenedOrders();
        call.enqueue(this);
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
