package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ListModifierKit extends BaseAdapter implements ListAdapter {

    private List<String> listNomsItems;
    private Context context;
    private String nomKit;

    public ListModifierKit(List<String> listNomsItems, String nomKit, Context context) {
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

        List<String> items = MainActivity.accesLocal.getListItems(nomKit);


        for (String unItem : items) {
            if (getItem(position).equals(unItem)) {
                cocherItem.setChecked(true);
            }

        }

        cocherItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cocherItem.isChecked()) {
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName("com.example.pierre.jeuxdesocit." + getItem(position));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Constructor<?> constructor = null;
                    try {
                        constructor = clazz.getConstructor();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    try {
                        MainActivity.accesLocal.ajoutItem((Item) constructor.newInstance(), nomKit);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    MainActivity.accesLocal.supprimerItem(getItem(position), nomKit);
                }
            }
        });

        return view;
    }
}