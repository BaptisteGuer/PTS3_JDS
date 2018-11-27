package com.example.pierre.jeuxdesocit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private List<String> listNomsKits;
    private Context context;
    private Dialog popup;

    public MyCustomAdapter(List<String> listNomsKits, Context context) {
        this.listNomsKits = listNomsKits;
        this.context = context;
        popup = new Dialog(context);
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list_kits, null);
        }

        TextView listItemText = view.findViewById(R.id.textView2);
        listItemText.setText(getItem(position));

        ImageButton param = view.findViewById(R.id.settings);
        ImageButton suppr = view.findViewById(R.id.delete);
        Button jouer = view.findViewById(R.id.play);

        Log.e("taille",view.getHeight()+"");


        suppr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ShowPopup(v,position);
                //listNomsKits.remove(position);
                notifyDataSetChanged();
            }
        });

        jouer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParametrageActivity.class);
                context.startActivity(intent);

            }
        });

        param.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifierKitActivity.class);
//                intent.putExtra("nomKit", getItem(position));
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void ShowPopup (View v,final int position){
        TextView txtclose;
        Button btnValider;
        TextView texte;
        popup.setContentView(R.layout.custom_popup);
        texte = (TextView) popup.findViewById(R.id.texte);
        txtclose = (TextView) popup.findViewById(R.id.croix);
        btnValider = (Button) popup.findViewById(R.id.valider);
        texte.setText("Voulez vous vraiment supprimer le kit "+getItem(position)+" ?");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNomsKits.remove(position);
                popup.dismiss();
                notifyDataSetChanged();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }
}