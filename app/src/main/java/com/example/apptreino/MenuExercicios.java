package com.example.apptreino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuExercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercicios);


    }

    public void acessarExercicio(View view)
    {
        Intent intent = new Intent( this, ReprodutorExercicios.class );
        startActivity(intent);
    }

    public void alongamentos(View view)
    {

    }
}