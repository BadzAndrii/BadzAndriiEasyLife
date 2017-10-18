package com.androidbash.androidbashfirebaseupdated;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrdersClientList extends ArrayAdapter<Order> {
    private Activity context;
    List<Order> orders;

    public OrdersClientList(Activity context, List<Order> orders) {
        super(context, R.layout.layout_service_list, orders);
        this.context = context;
        this.orders = orders;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_orderclient_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);

        Order order = orders.get(position);
        textViewName.setText(order.getCompanyId());
        textViewGenre.setText(order.getServiceId());
        textViewTime.setText(order.getStartTime());

        return listViewItem;
    }
}