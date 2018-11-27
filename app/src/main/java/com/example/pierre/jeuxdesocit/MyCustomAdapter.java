package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private List<String> listNomsKits;
    private Context context;

    public MyCustomAdapter(List<String> listNomsKits, Context context) {
        this.listNomsKits = listNomsKits;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listNomsKits.size();
    }

    @Override
    public String getItem(int pos) {
        return listNomsKits.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_kits, null);
        }

        TextView listItemText = view.findViewById(R.id.textView2);
        listItemText.setText(getItem(position));

        ImageButton modifierKit = view.findViewById(R.id.settings);
        ImageButton supprimerKit = view.findViewById(R.id.delete);
        Button jouerKit = view.findViewById(R.id.play);

        supprimerKit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.accesLocal.supprimerKit(listNomsKits.get(position));
                listNomsKits.remove(position);
                notifyDataSetChanged();
            }
        });

        jouerKit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParametrageActivity.class);
                context.startActivity(intent);
            }
        });

        modifierKit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifierKitActivity.class);
                intent.putExtra("nomKit", getItem(position));
                context.startActivity(intent);
            }
        });

        return view;
    }
}