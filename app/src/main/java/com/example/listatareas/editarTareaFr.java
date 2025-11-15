package com.example.listatareas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.listatareas.databinding.FragmentEditarTareaBinding;
import com.example.listatareas.databinding.FragmentMostrarDetalleTareaBinding;


public class editarTareaFr extends Fragment {
    private FragmentEditarTareaBinding binding;
    private TareaViewModel tareaViewModel;
    private NavController navController;
    private Tarea tareaParaEditar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentEditarTareaBinding.inflate(inflater,container,false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tareaViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);
        navController = Navigation.findNavController(view);

        tareaParaEditar = tareaViewModel.getTareaSeleccionada().getValue();

        if(tareaParaEditar !=null) {
            binding.editarNombre.setText(tareaParaEditar.nombre);
            binding.editarDescripcion.setText(tareaParaEditar.descripcion);
            binding.ratingEdit.setRating(tareaParaEditar.valoracion);
        }


        binding.btnGuardarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
                navController.popBackStack();
            }
        });


    }

    private void guardarCambios(){
        if (tareaParaEditar == null) {
            return; // Na hece nada si no hay tarea
        }

        // Leemos los campos
        String nuevoNombre = binding.editarNombre.getText().toString();
        String nuevaDescripcion = binding.editarDescripcion.getText().toString();
        float nuevaValoracion = binding.ratingEdit.getRating();

        // Modificamos la tarea con los nuevos valores
        tareaParaEditar.nombre = nuevoNombre;
        tareaParaEditar.descripcion = nuevaDescripcion;
        tareaParaEditar.valoracion = nuevaValoracion;

        tareaViewModel.actualizar(tareaParaEditar);
    }
}