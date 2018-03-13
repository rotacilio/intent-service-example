package br.com.rotacilio.intentserviceexample.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.rotacilio.intentserviceexample.MainActivity;
import br.com.rotacilio.intentserviceexample.models.Order;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public class OpenedOrdersService extends IntentService {

    public static final String MESSENGER_EXTRA = "br.com.rotacilio.intentserviceexample.MESSENGER_EXTRA";

    public OpenedOrdersService() {
        super("Opened Orders Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Messenger messenger = (Messenger) intent.getExtras().get(MESSENGER_EXTRA);
        Message msg = Message.obtain();
        try {
            String resultMessage = intent.getStringExtra(MainActivity.SERVICE_MESSAGE);
            msg.arg1 = Activity.RESULT_OK;
            msg.obj = this.getOpenedOrders();
            Log.d("SERVICE_LOG", "Runed from intent service");

            messenger.send(msg);
        } catch (Exception e) {
            Log.e("ERROR_SERVICE_1", e.getMessage());
            msg.arg1 = Activity.RESULT_CANCELED;
            msg.obj = e;
        }
    }

    private List<Order> getOpenedOrders() {
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
}
