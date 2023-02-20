package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;


public class FlashcardAnswer extends Fragment {
    View view;
    TextView answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_flashcard_answer, container, false);
        answer = view.findViewById(R.id.flashcardAnswer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String answerText = requireArguments().getString("answer");
        answer.setText(answerText);
    }

}