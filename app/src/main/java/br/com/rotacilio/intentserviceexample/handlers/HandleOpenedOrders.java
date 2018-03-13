package br.com.rotacilio.intentserviceexample.handlers;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import br.com.rotacilio.intentserviceexample.MainActivity;
import br.com.rotacilio.intentserviceexample.models.Order;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public class HandleOpenedOrders extends Handler {

    private MainActivity activity = null;

    public HandleOpenedOrders(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.arg1 == Activity.RESULT_OK) {
            activity.showResult((List<Order>) msg.obj);
        } else {
            activity.showError((Exception) msg.obj);
        }
    }
}
