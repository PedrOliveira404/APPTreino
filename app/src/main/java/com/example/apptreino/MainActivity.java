package com.example.apptreino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMenu();
            }
        }, 2000);
    }


    private void mostrarMenu()
    {
        Intent intent = new Intent( this, NivelExercicio.class );
        startActivity(intent);
        finish();
    }
}