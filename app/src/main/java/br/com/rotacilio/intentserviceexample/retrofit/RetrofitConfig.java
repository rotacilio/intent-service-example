package br.com.rotacilio.intentserviceexample.retrofit;

import br.com.rotacilio.intentserviceexample.retrofit.services.OrdersService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://172.100.10.132:3000/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public OrdersService getOrdersService() {
        return this.retrofit.create(OrdersService.class);
    }
}
