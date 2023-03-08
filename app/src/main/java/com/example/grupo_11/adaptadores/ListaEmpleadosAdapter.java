package com.example.grupo_11.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grupo_11.R;
import com.example.grupo_11.entidades.Empleados;
import com.example.grupo_11.VerEmpleados;



import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {

    ArrayList<Empleados> listaEmpleados;
    ArrayList<Empleados> listaOriginal;

    // Constructor de la clase
    public ListaEmpleadosAdapter(ArrayList<Empleados> listaEmpleados){
        this.listaEmpleados = listaEmpleados;
        listaOriginal =new ArrayList<>();
        listaOriginal.addAll(listaEmpleados);
    }

    // Crear el ViewHolder
    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleados, null, false);
        return new EmpleadoViewHolder(view);
    }

    // Vincular los datos del empleado con las vistas correspondientes en el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewNombre.setText(listaEmpleados.get(position).getNombre());
        holder.viewApellidos.setText(listaEmpleados.get(position).getApellidos());
        holder.viewDepartamento.setText(listaEmpleados.get(position).getDepartamento());
    }

    // Método para filtrar la lista de empleados según un término de búsqueda
    public void filtrado (String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud==0){
            listaEmpleados.clear();
            listaEmpleados.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                // Filtrar la lista usando una expresión lambda (Java 8 o superior)
                List<Empleados> collecion = listaEmpleados.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaEmpleados.clear();;
                listaEmpleados.addAll(collecion);
            }else {
                // Filtrar la lista iterando sobre cada elemento (Java 7 o inferior)
                for (Empleados e: listaOriginal){
                    if (e.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaEmpleados.add(e);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    // Devolver el número de empleados en la lista
    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    // Clase interna para el ViewHolder
    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellidos, viewDepartamento;

        // Constructor de la clase
        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewDepartamento = itemView.findViewById(R.id.viewDepartamento);

            // Agregar un Listener para abrir la actividad de VerEmpleados al hacer clic en un elemento de la lista
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerEmpleados.class);

                    intent.putExtra("id", listaEmpleados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
