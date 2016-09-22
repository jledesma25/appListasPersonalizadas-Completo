package academiamoviles.com.applistas.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import academiamoviles.com.applistas.Interfaz.RecyclerViewOnItemClickListener;
import academiamoviles.com.applistas.Modelo.Alumno;
import academiamoviles.com.applistas.R;

/**
 * Created by jledesma on 19/09/2016.
 */
public class AlumnoAdapter extends
        RecyclerView.Adapter<AlumnoAdapter.AlumnosAdapterViewHolder> {

    Context context;
    int notas_row;
    ArrayList<Alumno> list_alumnos;
    private int lastAdded;

    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public AlumnoAdapter(Context context, int notas_row, ArrayList<Alumno> list_alumnos,RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {

        this.context = context;
        this.notas_row = notas_row;
        this.list_alumnos = list_alumnos;
        this.recyclerViewOnItemClickListener =  recyclerViewOnItemClickListener;

    }

    public void addItem(int position, Alumno data) {
        list_alumnos.add(position, data);
        notifyItemInserted(position);
        lastAdded = 1;

    }

    public void removeItem(int position) {
        list_alumnos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public AlumnosAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(notas_row, parent, false);
        return new AlumnosAdapterViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AlumnosAdapterViewHolder holder, int position) {
        Alumno alumno = list_alumnos.get(position);

       // if(lastAdded == 1 && position==0){
       //     holder.itemView.setBackgroundColor(Color.RED);
       // }else{
       //     holder.itemView.setBackgroundColor(Color.YELLOW);
       // }

        holder.tv_nombres.setText(alumno.getNombre());
        holder.tv_curso.setText(alumno.getCurso());
    }

    @Override
    public int getItemCount() {
        return list_alumnos.size();
    }

    public class AlumnosAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView tv_nombres,tv_curso;

        public AlumnosAdapterViewHolder(View itemView) {
            super(itemView);

            tv_nombres = (TextView) itemView.findViewById(R.id.tv_alumno);
            tv_curso = (TextView) itemView.findViewById(R.id.tv_curso);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}

