package com.androidbash.androidbashfirebaseupdated;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CompanyList extends ArrayAdapter<Company> {
    private Activity context;
    List<Company> companies;

    public CompanyList(Activity context, List<Company> companies) {
        super(context, R.layout.layout_company_list, companies);
        this.context = context;
        this.companies = companies;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_company_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Company company = companies.get(position);
        textViewName.setText(company.getCompanyName());
        textViewGenre.setText(company.getCompanyDescription());




        return listViewItem;
    }
}