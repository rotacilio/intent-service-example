package br.com.rotacilio.intentserviceexample.retrofit.services;

import br.com.rotacilio.intentserviceexample.retrofit.models.Order;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public interface OrdersService {

    @GET("orders/opened")
    Call<Order> getOpenedOrders();

}