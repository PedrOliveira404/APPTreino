package com.example.apptreino;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class ReprodutorExercicios extends AppCompatActivity {
    private int prog = 0, repet = 0, numExercicios = 0, cont = 0;

    private TextView txtTitulo, txtRepet, txtSerieCount;
    private ProgressBar barraRepet;
    private VideoView videoView;
    private String[] exercicios;
    private String path;
    private int progInc = 10; // Incrementa a barra de progresso para cada repetição
    private int numRepet = 100; // 10 repetições


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodutor_exercicios);

        txtTitulo = findViewById(R.id.textViewTitulo);
        txtRepet = findViewById(R.id.progress_text);
        txtSerieCount = findViewById(R.id.textViewSerieCont);

        barraRepet = findViewById(R.id.progress_bar);
        barraRepet.setProgress(0);

        videoView = findViewById(R.id.videoView);
        videoView.setZOrderOnTop(true);
        path = "android.resource://" + getPackageName() + "/";

        exercicios = getResources().getStringArray(R.array.Playlist1);
        numExercicios = exercicios.length - 1;

        contSerie();
    }

    protected void onResume()
    {
        super.onResume();
        reproduzVideo();
    }

    public void btnProximo(View view)
    {
        pulaVideo();
    }

    public void btnAnterior(View view)
    {
        retornaVideo();
    }

    private void contSerie()
    {
        txtSerieCount.setText(String.valueOf(cont + " / " + numExercicios));
    }

    private void inserirTitulo()
    {
        txtTitulo.setText(exercicios[cont]
                .substring(0, 1).toUpperCase() + exercicios[cont].substring(1)
                .replace("_", " "));
    }

    private int getVideoID(String name) { return getResources().getIdentifier(name, "raw", getPackageName()); }

    public void reproduzVideo()
    {
        selecionaVideo();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (prog < numRepet) incrementaProg();
                else if (cont < numExercicios) pulaVideo();
                else showAlertBox();
            }
        });
    }

    private void selecionaVideo()
    {
        try
        {
            inserirTitulo();
            videoView.setVideoPath(path + getVideoID(exercicios[cont]));
        }
        catch (Exception e)
        {
            showErrorBox(e.toString());
        }
    }

    private void pulaVideo()
    {
        if (cont >= numExercicios)
            showAlertBox();
        else
        {
            cont++;
            prog = repet = 0;

            contSerie();
            reiniciaProg();
            selecionaVideo();
            videoView.start();
        }

    }


    private void retornaVideo()
    {
        cont = cont > 0 ? cont - 1 : 0;
        prog = repet = 0;

        contSerie();
        reiniciaProg();
        selecionaVideo();
        videoView.start();
    }

    private void incrementaProg()
    {
        barraRepet.setProgress(prog += progInc);
        txtRepet.setText(String.valueOf(++repet));
        videoView.start();
    }

    private void reiniciaProg()
    {
        prog = repet = 0;
        barraRepet.setProgress(prog);
        txtRepet.setText(String.valueOf(repet));
        contSerie();
    }

    private void reiniciarVideo()
    {
        cont = 0;
        videoView.seekTo(0);
        selecionaVideo();
        reiniciaProg();

        if (videoView.isPlaying() == false)
            videoView.start();
    }



    public void showAlertBox()
    {
        videoView.pause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.alertTitulo);
        builder.setMessage(R.string.alertMsg);

        builder.setPositiveButton(R.string.alertSim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                 reiniciarVideo();
                 finish();// Volta ao menu
            }
        });
        builder.setNegativeButton(R.string.alertNao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                reiniciarVideo();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void showErrorBox(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Erro inesperado");
        builder.setMessage(msg);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}