package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Kit> list = new ArrayList<Kit>();
    private Context context;



    public MyCustomAdapter(ArrayList<Kit> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
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
            view = inflater.inflate(R.layout.list_custom, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView2);
        listItemText.setText(getItem(position).toString());

        //Handle buttons and add onClickListeners
        Button params = (Button)view.findViewById(R.id.settings);
        Button supp = (Button)view.findViewById(R.id.delete);
        Button jouer = (Button)view.findViewById(R.id.play);


        supp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        jouer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ParamActivity.class);
                intent.putExtra("name", getItem(position).toString());
                context.startActivity(intent);

            }
        });
        params.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });
        return view;
    }
}