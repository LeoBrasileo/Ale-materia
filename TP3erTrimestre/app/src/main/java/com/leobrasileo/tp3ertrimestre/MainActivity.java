package com.leobrasileo.tp3ertrimestre;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Boolean visNOTA1 = false;
    Boolean visNOTA2 = false;
    Boolean visNOTA3 = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.listView1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button bt1 = (Button) toolbar.findViewById(R.id.button1);
        Button bt2 = (Button) toolbar.findViewById(R.id.button2);
        Button bt3 = (Button) toolbar.findViewById(R.id.button3);
        setSupportActionBar(toolbar);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ObjetoMateria> materias = new ArrayList<ObjetoMateria>();
                materias.add(new ObjetoMateria("Matematica",5,4,8,true,false,false));
                materias.add(new ObjetoMateria("Literatura",6,7,8,true,false,false));
                materias.add(new ObjetoMateria("Biologia",6,3,6,true,false,false));
                materias.add(new ObjetoMateria("Historia",9,10,1,true,false,false));
                materias.add(new ObjetoMateria("Analógica con Maxim",3,2,3,true,false,false));
                materias.add(new ObjetoMateria("Analógica sin Maxim",10,9,10,true,false,false));

                Adapter adapter = new Adapter(MainActivity.this,materias);
                listView.setAdapter(adapter);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ObjetoMateria> materias = new ArrayList<ObjetoMateria>();
                materias.add(new ObjetoMateria("Matematica",5,4,8,false,true,false));
                materias.add(new ObjetoMateria("Literatura",6,7,8,false,true,false));
                materias.add(new ObjetoMateria("Biologia",6,3,6,false,true,false));
                materias.add(new ObjetoMateria("Historia",9,10,1,false,true,false));
                materias.add(new ObjetoMateria("Analógica con Maxim",3,2,3,false,true,false));
                materias.add(new ObjetoMateria("Analógica sin Maxim",10,9,10,false,true,false));

                Adapter adapter = new Adapter(MainActivity.this,materias);
                listView.setAdapter(adapter);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ObjetoMateria> materias = new ArrayList<ObjetoMateria>();
                materias.add(new ObjetoMateria("Matematica",5,4,8,false,false,true));
                materias.add(new ObjetoMateria("Literatura",6,7,8,false,false,true));
                materias.add(new ObjetoMateria("Biologia",6,3,6,false,false,true));
                materias.add(new ObjetoMateria("Historia",9,10,1,false,false,true));
                materias.add(new ObjetoMateria("Analógica con Maxim",3,2,3,false,false,true));
                materias.add(new ObjetoMateria("Analógica sin Maxim",10,9,10,false,false,true));

                Adapter adapter = new Adapter(MainActivity.this,materias);
                listView.setAdapter(adapter);
            }
        });
    }

    class Adapter extends ArrayAdapter<ObjetoMateria>
    {

        private Activity context;
        public ArrayList<ObjetoMateria> listMaterias;

        class ViewHolder
        {
            TextView txtMateria;
            TextView nota1;
            TextView nota2;
            TextView nota3;
        }

        Adapter(Activity context,ArrayList<ObjetoMateria> listMaterias)
        {
            super(context, R.layout.item_materias, listMaterias);
            this.context = context;
            this.listMaterias = listMaterias;
        }

        public ArrayList<ObjetoMateria> getArrayList() {
            return listMaterias;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View item = convertView;
            ViewHolder holder;

            if(item == null)
            {
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.item_materias, null);

                holder = new ViewHolder();
                holder.txtMateria = item.findViewById(R.id.textViewMat);
                holder.nota1 = item.findViewById(R.id.textViewnot1);
                holder.nota2 = item.findViewById(R.id.textViewnot2);
                holder.nota3 = item.findViewById(R.id.textViewnot3);
                item.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }

            if (listMaterias.get(position).isVisNot1() == true)
            {
                holder.nota1.setVisibility(View.VISIBLE);
            }else {
                holder.nota1.setVisibility(View.INVISIBLE);
            }

            if (listMaterias.get(position).isVisNot2() == true)
            {
                holder.nota2.setVisibility(View.VISIBLE);
            }else {
                holder.nota2.setVisibility(View.INVISIBLE);
            }

            if (listMaterias.get(position).isVisNot3() == true)
            {
                holder.nota3.setVisibility(View.VISIBLE);
            }else {
                holder.nota3.setVisibility(View.INVISIBLE);
            }

            holder.txtMateria.setText(listMaterias.get(position).getNombreMateria());
            holder.nota1.setText(String.valueOf(listMaterias.get(position).getNota1()));
            holder.nota2.setText(String.valueOf(listMaterias.get(position).getNota2()));
            holder.nota3.setText(String.valueOf(listMaterias.get(position).getNota3()));
            return(item);
        }
    }
}
