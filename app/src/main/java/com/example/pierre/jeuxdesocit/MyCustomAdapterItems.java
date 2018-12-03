package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapterItems extends BaseAdapter implements ListAdapter {

    private List<String> listNomsItems;
    private Context context;
    private String nomKit;

    public MyCustomAdapterItems(List<String> listNomsItems, String nomKit, Context context) {
        this.listNomsItems = listNomsItems;
        this.context = context;
        this.nomKit = nomKit;
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

        List<String> items = new ArrayList<String>();

        items = MainActivity.accesLocal.getListItems(nomKit);


        for (String unItem : items) {
            Log.i("AAAAAA", getItem(position) + " ET " + unItem);
            if (getItem(position).equals(unItem)) {
                cocherItem.setChecked(true);
            }

        }


        cocherItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cocherItem.isChecked()) {
                    // ajouter item a la liste des items à mettre dans la bdd
                    MainActivity.accesLocal.ajoutItem(new Item(getItem(position), ""), nomKit);
                } else {
                    // supprimer item a la liste des items à mettre dans la bdd
                    Log.i("ZZZZZZ", "" + MainActivity.accesLocal.getItem(getItem(position), nomKit));
                    //MainActivity.accesLocal.supprimerItem(MainActivity.accesLocal.getItem(getItem(position), nomKit), nomKit);
                }
            }
        });

        return view;
    }
}