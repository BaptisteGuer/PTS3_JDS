package com.example.pierre.jeuxdesocit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter, Filterable {

    private List<String> listNomsKits;
    private List<String> listNomsKitsFiltree;
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

        ImageButton modifierKit = view.findViewById(R.id.settings);
        ImageButton supprimerKit = view.findViewById(R.id.delete);
        Button jouerKit = view.findViewById(R.id.play);

        supprimerKit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ShowPopup(v,position);
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

    @Override
    public Filter getFilter()
    {
        Filter filter = new Filter()
        {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results)
            {

                listNomsKits = (List<String>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<String> FilteredArrList = new ArrayList<String>();

                if (listNomsKitsFiltree == null)
                {
                    System.out.println("");
                    listNomsKitsFiltree = new ArrayList<String>(listNomsKits); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0)
                {
                    // set the Original result to return
                    results.count = listNomsKitsFiltree.size();
                    results.values = listNomsKitsFiltree;
                }
                else
                {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listNomsKitsFiltree.size(); i++)
                    {
                        String data = listNomsKitsFiltree.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString()))
                        {
                            FilteredArrList.add(data);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
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
                MainActivity.accesLocal.supprimerKit(listNomsKits.get(position));
                listNomsKits.remove(position);
                popup.dismiss();
                notifyDataSetChanged();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.show();
    }

}