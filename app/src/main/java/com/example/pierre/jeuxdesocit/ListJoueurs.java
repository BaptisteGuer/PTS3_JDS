package com.example.pierre.jeuxdesocit;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;


public class ListJoueurs extends BaseAdapter implements ListAdapter {

    private Context context;
    private Dialog popup;
    private String nomKit;

    public ListJoueurs(String nomKit, Context context){
        this.context = context;
        this.nomKit = nomKit;
        popup = new Dialog(context);
    }


    @Override
    public int getCount() {
    return ParametrageActivity.lesJoueurs.size();
    }

    @Override
    public Joueur getItem(int pos) {
        return ParametrageActivity.lesJoueurs.get(pos);
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
            view = inflater.inflate(R.layout.custom_list_joueurs, null);
        }

        TextView listJoueursText = view.findViewById(R.id.nom);
        listJoueursText.setText(getItem(position).getNom());

        ImageButton supprimerKit = view.findViewById(R.id.delete);
        supprimerKit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParametrageActivity.lesJoueurs.remove(ParametrageActivity.lesJoueurs.get(position));
                notifyDataSetChanged();
            }
        });



        return view;
    }


}
