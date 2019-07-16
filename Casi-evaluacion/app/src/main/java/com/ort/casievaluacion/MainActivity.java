package com.ort.casievaluacion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    private Adapter1 adapter1;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        toolbar1 = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar1);
        // esto es para la flechita de atras
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bienvenido usuario logueado");

        ArrayList<Personas> personas = new ArrayList<Personas>();

        personas.add(new Personas("Leonel","Braginski","leobraginski@gmail.com","oliver"));

        adapter1 = new Adapter1(this,personas);
        listView.setAdapter(adapter1);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_show:

                Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();

                break;
        }

        return true;
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
