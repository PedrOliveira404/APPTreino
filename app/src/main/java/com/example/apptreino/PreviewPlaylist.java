package com.example.apptreino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;


public class PreviewPlaylist extends AppCompatActivity {

    Intent intent;
    int playlist = 0, numExercicios = 0;
    String[] exercicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_playlist);

        Bundle extras = getIntent().getExtras();

        TextView nivel = findViewById(R.id.textViewNivelSelecionado);
        nivel.setText(extras.getString("nivel"));

        playlist = extras.getInt("playlist", 0);
        exercicios = getResources().getStringArray(playlist);

        numExercicios = exercicios.length - 1;

        injectView();
    }

    private int getImageID(String name) { return getResources().getIdentifier(name, "drawable", getPackageName()); }

    private void inserirTitulo(TextView txt, int index)
    {
        txt.setText(exercicios[index]
                .substring(0, 1).toUpperCase() + exercicios[index].substring(1)
                .replace("_", " "));
    }

    private void injectView()
    {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v[] = new View[numExercicios];

        TextView txtCont, txtExercicio;
        ImageView img;
        // insert into main view
        LinearLayout insertPoint = findViewById(R.id.linearPlaylist);
        int i = 0;
        for (int index = 0, cont = 1; index <= numExercicios; index++)
        {
            if (exercicios[index].equals("descanso")) continue; // Pula iteração que contém descanso

            v[i] = vi.inflate(R.layout.card_layout, null);

            txtCont = v[i].findViewById(R.id.textViewCont);
            txtCont.setText(String.valueOf(cont++ + "°"));


            txtExercicio = v[i].findViewById(R.id.textViewExercicio);
            inserirTitulo(txtExercicio, index);

            img = v[i].findViewById(R.id.imageViewExercicio);
            img.setImageResource(getImageID(exercicios[index]));


            i++;
        }

        // Inverte a ordem dos exercícios
        for (int y = i - 1; y >= 0; y--)
        {
            insertPoint.addView(v[y], 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
    }


    public void iniciarExercicio(View view)
    {
        Intent intent = new Intent( this, ReprodutorExercicios.class );
        intent.putExtra("playlist", playlist);
        startActivity(intent);
    }
}