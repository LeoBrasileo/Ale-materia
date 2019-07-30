package com.ort.casievaluacion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    private Adapter1 adapter1;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private FirebaseDatabase database;
    private DatabaseReference todo;
    private DatabaseReference personasdb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        toolbar1 = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this,"bd_personas",null,1);

        database = FirebaseDatabase.getInstance();
        personasdb = database.getReference("personas");
        todo = database.getReference();

        final Bundle bundle = this.getIntent().getExtras();
        String nombreuser = bundle.getString("nombreuser");
        String mailsincom = bundle.getString("mailsincom");

        setSupportActionBar(toolbar1);
        // esto es para la flechita de atras        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bienvenido " + nombreuser);


        todo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this,RegistrarPersona.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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

            case R.id.action_remove:
                Toast.makeText(this, "Selecciona que persona eliminar", Toast.LENGTH_SHORT).show();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                        if (position == 0)
                        {
                            return;
                        }


                        //necesito eliminar la rama del usuario que toco, para eso tengo que conseguir su mail al apretarlo
                        Adapter1 adapter = (Adapter1) listView.getAdapter();
                        ArrayList<Personas> listPersonas = adapter.getArrayList();

                        String mail = String.valueOf(listPersonas.get(position).getEmail());
                        final String mailsincom = mail.replace(".com","");



                        personasdb.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Personas eliminar = dataSnapshot.child(String.valueOf(position)).getValue(Personas.class);
                                //getupdatesEliminar(dataSnapshot, position, personasdb);
                                personasdb.child(mailsincom).removeValue();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

                break;

            case R.id.action_logout:
                SharedPreferences sharedPreferences = getSharedPreferences
                        ("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user","");
                editor.putString("pass","");
                editor.commit();

                Intent intent =
                        new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
                break;
        }

        return true;
    }

    class Adapter1 extends ArrayAdapter<Personas>
    {

        private Activity context;
        public ArrayList<Personas> listPersonas;

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

        public ArrayList<Personas> getArrayList() {
            return listPersonas;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View item = convertView;
            ViewHolder holder;

            String mail = String.valueOf(listPersonas.get(position).getEmail());

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
            holder.txtApellido.setText(String.valueOf(listPersonas.get(position).getApelido()));
            holder.txtEmail.setText(String.valueOf(listPersonas.get(position).getEmail()));
            return(item);
        }
    }


    public void getupdates(DataSnapshot dataSnapshot) {

        ArrayList<Personas> personas = new ArrayList<Personas>();
        adapter1 = new Adapter1(this,personas);
        listView.setAdapter(adapter1);

        personas.clear();
        personas.add(new Personas("Nombre:","Apellido:","Email:",""));

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Personas d = new Personas();
            d.setNombre(ds.getValue(Personas.class).getNombre());
            d.setApelido(ds.getValue(Personas.class).getApelido());
            d.setEmail(ds.getValue(Personas.class).getEmail());
            d.setPass(ds.getValue(Personas.class).getPass());
            personas.add(d);
        }
    }

    public void getupdatesEliminar(DataSnapshot dataSnapshot, int position, DatabaseReference reference) {

        ArrayList<Personas> personas = new ArrayList<Personas>();
        adapter1 = new Adapter1(this,personas);
        listView.setAdapter(adapter1);

        personas.clear();
        personas.add(new Personas("Nombre:","Apellido:","Email:",""));

        Personas e = new Personas();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Personas d = new Personas();
            d.setNombre(ds.getValue(Personas.class).getNombre());
            d.setApelido(ds.getValue(Personas.class).getApelido());
            d.setEmail(ds.getValue(Personas.class).getEmail());
            d.setPass(ds.getValue(Personas.class).getPass());

            String mail = d.getEmail().toString();
            final String mailsincom = mail.replace(".com","");
            personasdb.child(mailsincom).removeValue();

            personas.add(d);
        }
    }
}
