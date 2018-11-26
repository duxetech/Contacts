package com.duxetech.mycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Karthik Swamy on 12/11/18.
 */

class CustAdapter extends BaseAdapter {
    List<Contacts> list;
    Context contactsList;
    TextView tv1, tv2, tv3,tv4;
    Contacts contact;

    public CustAdapter(ContactsList contactsList, List<Contacts> list) {
        this.list = list;
        this.contactsList = contactsList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inf = (LayoutInflater) contactsList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inf.inflate(R.layout.listview_item,parent,false);
        tv1 = view.findViewById(R.id.tv1);
        tv3 = view.findViewById(R.id.tv3);
        contact = list.get(position);
        tv1.setText(contact.getFirst_name());
        tv3.setText(contact.getMobile());
        return view;
    }
}
