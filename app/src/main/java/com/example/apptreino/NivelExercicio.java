package com.example.apptreino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NivelExercicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_exercicio);
    }


    private void acessarExercicio(int playlist, String nivel)
    {
        Intent intent = new Intent( this, PreviewPlaylist.class );
        Bundle extras = new Bundle();

        extras.putInt("playlist", playlist);
        extras.putString("nivel", nivel);

        intent.putExtras(extras);
        startActivity(intent);
    }

    public void btnLeve(View view) { acessarExercicio(R.array.Leve, getResources().getString(R.string.btnLeve)); }

    public void btnModerado(View view) { acessarExercicio(R.array.Moderado, getResources().getString(R.string.btnModerado)); }

    public void btnIntenso(View view) { acessarExercicio(R.array.Intenso, getResources().getString(R.string.btnIntenso)); }


}

