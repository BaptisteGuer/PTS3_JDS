package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class MyCustomAdapterItems extends BaseAdapter implements ListAdapter {

    private List<String> listNomsItems;
    private Context context;

    public MyCustomAdapterItems(List<String> listNomsItems, Context context) {
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

        TextView nomItem = view.findViewById(R.id.nomItem);
        nomItem.setText(getItem(position));

        final CheckBox cocherItem = view.findViewById(R.id.checkBox);

        cocherItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cocherItem.isChecked()){
                    // ajouter item a la liste des items à mettre dans la bdd
                    ModifierKitActivity.itemsOK.add(getItem(position));
                } else {
                    // supprimer item a la liste des items à mettre dans la bdd
                    for (int i = 0; i < ModifierKitActivity.itemsOK.size(); i++) {
                        if(ModifierKitActivity.itemsOK.get(i) == getItem(position)){
                            ModifierKitActivity.itemsOK.remove(i);
                        }
                    }
                }
            }
        });

        return view;
    }
}