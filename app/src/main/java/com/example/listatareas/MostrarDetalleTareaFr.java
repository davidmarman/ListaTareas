package com.example.listatareas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listatareas.databinding.FragmentMostrarDetalleTareaBinding;


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


    }
}