package academiamoviles.com.applistas;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import academiamoviles.com.applistas.Adapter.AlumnoAdapter;
import academiamoviles.com.applistas.Interfaz.IMainActivity;
import academiamoviles.com.applistas.Interfaz.RecyclerViewOnItemClickListener;
import academiamoviles.com.applistas.Modelo.Alumno;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @InjectView(R.id.recyclerview_alumnos)
    RecyclerView recyclerview_alumnos;

    @InjectView(R.id.fabAgregar)
    android.support.design.widget.FloatingActionButton fabAgregar;

    private AlumnoAdapter mAdapter;
    ArrayList<Alumno> list_alumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        configurarToolbar();
        inicializarValores();
        setearAdaptador();
        configurarOrientacionLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mostrarMensaje("Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarMensaje("Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mostrarMensaje("Stop");
    }

    @Override
    public void inicializarValores() {

        list_alumnos = new ArrayList<>();

        Alumno alumno1 = new Alumno();
        alumno1.setNombre("Jose");
        alumno1.setCurso("Android");

        list_alumnos.add(alumno1);

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Freddy");
        alumno2.setCurso("IOS");

        list_alumnos.add(alumno2);
    }

    @Override
    public void setearAdaptador() {

        mAdapter = new AlumnoAdapter(this, R.layout.alumno_row, list_alumnos, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mAdapter.removeItem(position);
            }
        });
        recyclerview_alumnos.setAdapter(mAdapter);
        configurarAnimator();
    }

    @Override
    public void mostrarMensaje(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void configurarOrientacionLayout() {
        recyclerview_alumnos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void configurarToolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Listado de Alumnos");
    }

    @Override
    public void configurarAnimator() {
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerview_alumnos.setItemAnimator(itemAnimator);
    }

    @Override
    @OnClick(R.id.fabAgregar)
    public void agregarAlumno() {
        Intent intent = new Intent(this, AgregarAlumnoActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("NOMBRES");
            String resultado2 = data.getExtras().getString("EDAD");

            Alumno alumno = new Alumno();
            alumno.setNombre(resultado);
            alumno.setCurso(resultado2);

            mAdapter.addItem(list_alumnos.size() - (list_alumnos.size()), alumno);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:

                int total = list_alumnos.size();

                for( int i=0;i<total;i++){
                    mAdapter.removeItem(0);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
