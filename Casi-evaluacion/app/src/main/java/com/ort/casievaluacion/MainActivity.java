package com.ort.casievaluacion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    class Adapter1 extends ArrayAdapter<Personas>
    {

        private Activity context;
        private ArrayList<Personas> listPersonas;

        class ViewHolder
        {
            TextView txtNombre;
            TextView txtApellido;
            TextView txtEmail;

        }

        Adapter1(Activity context,ArrayList<Personas> listPersonas)
        {
            super(context, R.layout.item, listPersonas);
            this.context = context;
            this.listPersonas = listPersonas;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View item = convertView;
            ViewHolder holder;

            if(item == null)
            {
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.item, null);

                holder = new ViewHolder();
                holder.txtNombre = item.findViewById(R.id.textView);
                holder.txtApellido = item.findViewById(R.id.textView2);
                holder.txtEmail = item.findViewById(R.id.textView3);
                item.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }

            holder.txtNombre.setText(listPersonas.get(position).getNombre());
            holder.txtApellido.setText(String.valueOf(listPersonas.get(position).getAppelido()));
            holder.txtEmail.setText(String.valueOf(listPersonas.get(position).getEmail()));
            return(item);
        }
    }
}
