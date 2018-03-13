package br.com.rotacilio.intentserviceexample.retrofit.models;

/**
 * Created by rodrigo.lins on 13/03/2018.
 */

public class OrderItem {

    private String title;
    private String description;
    private String value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
