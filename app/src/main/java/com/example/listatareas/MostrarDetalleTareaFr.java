package com.example.listatareas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.listatareas.databinding.FragmentMostrarDetalleTareaBinding;

import java.util.List;


public class MostrarDetalleTareaFr extends Fragment {

    private FragmentMostrarDetalleTareaBinding binding;
    private TareaViewModel tareaViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentMostrarDetalleTareaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tareaViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

        tareaViewModel.getTareaSeleccionada().observe(getViewLifecycleOwner(), new Observer<Tarea>() {
            @Override
            public void onChanged(Tarea tarea) {
                binding.descripcion.setText(tareaViewModel.getTareaSeleccionada().getValue().descripcion);
                binding.nombre.setText(tareaViewModel.getTareaSeleccionada().getValue().nombre);
                binding.valoracion.setRating(tareaViewModel.getTareaSeleccionada().getValue().valoracion);

                binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if (fromUser){
                            tareaViewModel.actualizar(tarea,rating);
                        }
                    }
                });
            }
        });



        /*
        Esto funciona, pero solo sirve para visualizar datos. No funciona si lo que pretendemos es modificar los elementos
        binding.descripcion.setText(tareaViewModel.getTareaSeleccionada().descripcion);
        binding.nombre.setText(tareaViewModel.getTareaSeleccionada().nombre);
        binding.valoracion.setRating(tareaViewModel.getTareaSeleccionada().valoracion);
        */
    }
}