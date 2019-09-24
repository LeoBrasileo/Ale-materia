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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = (Button) findViewById(R.id.button1);
        Button bt2 = (Button) findViewById(R.id.button2);
        Button bt3 = (Button) findViewById(R.id.button3);
        ListView listView = (ListView) findViewById(R.id.listView1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<ObjetoMateria> materias = new ArrayList<ObjetoMateria>();
        materias.add(new ObjetoMateria("Matematica",5,4,8,false,false,false));
        materias.add(new ObjetoMateria("Literatura",6,7,8,false,false,false));
        materias.add(new ObjetoMateria("Biologia",6,3,6,false,false,false));
        materias.add(new ObjetoMateria("Historia",9,10,1,false,false,false));
        materias.add(new ObjetoMateria("Analógica con Maxim",3,2,3,false,false,false));
        materias.add(new ObjetoMateria("Analógica sin Maxim",10,9,10,false,false,false));
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

            holder.txtMateria.setText(listMaterias.get(position).getNombreMateria());
            holder.nota1.setText(String.valueOf(listMaterias.get(position).getNota1()));
            holder.nota2.setText(String.valueOf(listMaterias.get(position).getNota2()));
            holder.nota3.setText(String.valueOf(listMaterias.get(position).getNota3()));
            return(item);
        }
    }
}
