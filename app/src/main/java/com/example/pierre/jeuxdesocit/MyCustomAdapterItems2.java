package com.example.pierre.jeuxdesocit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class MyCustomAdapterItems2 extends BaseAdapter implements ListAdapter {

    private LinearLayout layout;
    private List<String> listNomsItems;
    private Context context;

    public MyCustomAdapterItems2(List<String> listNomsItems, Context context) {
        this.listNomsItems = listNomsItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listNomsItems.size();
    }

    @Override
    public String getItem(int pos) {
        return listNomsItems.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_items, null);
        }

        final TextView nomItem = view.findViewById(R.id.nomItem);
        nomItem.setText(getItem(position));

        final CheckBox cocherItem = view.findViewById(R.id.checkBox);

        cocherItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cocherItem.isChecked()){
                    CreerKitActivity.listItemsAAjouter.add(getItem(position));
                } else {
                    for (int i = 0; i < CreerKitActivity.listItemsAAjouter.size(); i++){
                        if (CreerKitActivity.listItemsAAjouter.get(i) == getItem(position)) CreerKitActivity.listItemsAAjouter.remove(i);
                    }
                }
            }
        });

        return view;
    }
}