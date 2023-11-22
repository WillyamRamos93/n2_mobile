package com.ritter.cursoextensao.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ritter.cursoextensao.data.CursoExtensaoViewModel;
import com.ritter.cursoextensao.databinding.FragmentCursoExtensaoBinding;

public class CursoExtensaoFragment extends Fragment {

    private FragmentCursoExtensaoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CursoExtensaoViewModel galleryViewModel =
                new ViewModelProvider(this).get(CursoExtensaoViewModel.class);

        binding = FragmentCursoExtensaoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCursoextensao;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}