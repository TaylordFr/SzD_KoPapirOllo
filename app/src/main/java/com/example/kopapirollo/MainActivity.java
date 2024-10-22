package com.example.kopapirollo;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private ImageView altalad_vsz;
    private ImageView gepaltal_vsz;
    private Button ko_btn;
    private Button papir_btn;
    private Button ollo_btn;
    private String ember_valasztott;
    private String gep_valasztott;
    private TextView e_eredmeny;
    private int e_eredmeny_szam = 0;
    private TextView g_eredmeny;
    private int g_eredmeny_szam = 0;
    private Random random = new Random();
    private final String[] lehetosegek = new String[]{"kő", "papír", "olló"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        ko_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    if(e_eredmeny_szam < 3 && g_eredmeny_szam < 3){
                        gep_valasztott = lehetosegek[random.nextInt(3)];
                        ember_valasztott = "kő";
                        altalad_vsz.setImageResource(R.drawable.rock);

                        if(gep_valasztott.equals("kő")){
                            gepaltal_vsz.setImageResource(R.drawable.rock);
                        } else if(gep_valasztott.equals("papír")){
                            gepaltal_vsz.setImageResource(R.drawable.paper);
                            g_eredmeny_szam++;
                            g_eredmeny.setText("Gép: " + g_eredmeny_szam);
                            Toast.makeText(MainActivity.this, "Vesztettél!", Toast.LENGTH_SHORT).show();

                        } else {
                            gepaltal_vsz.setImageResource(R.drawable.scissors);
                            e_eredmeny_szam++;
                            e_eredmeny.setText("Ember: " + e_eredmeny_szam + " ");
                            Toast.makeText(MainActivity.this, "Győztél!", Toast.LENGTH_SHORT).show();
                        }
                    } else if(e_eredmeny_szam == 3 || g_eredmeny_szam == 3){
                        showJatekVege();
                    }
                } catch (Exception e){
                    Log.e("Myapp", "Hiba történt");
                }
            }
        });

        papir_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if(e_eredmeny_szam < 3 && g_eredmeny_szam < 3){
                        gep_valasztott = lehetosegek[random.nextInt(3)];
                        ember_valasztott = "papír";
                        altalad_vsz.setImageResource(R.drawable.paper);

                        if(gep_valasztott.equals("kő")){
                            gepaltal_vsz.setImageResource(R.drawable.rock);
                            e_eredmeny_szam++;
                            e_eredmeny.setText("Ember: " + e_eredmeny_szam + " ");
                            Toast.makeText(MainActivity.this, "Győztél!", Toast.LENGTH_SHORT).show();
                        } else if(gep_valasztott.equals("papír")){
                            gepaltal_vsz.setImageResource(R.drawable.paper);
                        } else {
                            gepaltal_vsz.setImageResource(R.drawable.scissors);
                            g_eredmeny_szam++;
                            g_eredmeny.setText("Gép: " + g_eredmeny_szam);
                            Toast.makeText(MainActivity.this, "Vesztettél!", Toast.LENGTH_SHORT).show();
                        }
                    } else if(e_eredmeny_szam == 3 || g_eredmeny_szam == 3){
                        showJatekVege();
                    }

                } catch (Exception e){
                    Log.e("Myapp", "Hiba történt");
                }
            }
        });


        ollo_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if(e_eredmeny_szam < 3 && g_eredmeny_szam < 3){
                        gep_valasztott = lehetosegek[random.nextInt(3)];
                        ember_valasztott = "olló";
                        altalad_vsz.setImageResource(R.drawable.scissors);

                        if(gep_valasztott.equals("kő")){
                            gepaltal_vsz.setImageResource(R.drawable.rock);
                            g_eredmeny_szam++;
                            g_eredmeny.setText("Gép: " + g_eredmeny_szam);
                            Toast.makeText(MainActivity.this, "Vesztettél!", Toast.LENGTH_SHORT).show();
                        } else if(gep_valasztott.equals("papír")){
                            gepaltal_vsz.setImageResource(R.drawable.paper);
                            e_eredmeny_szam++;
                            e_eredmeny.setText("Ember: " + e_eredmeny_szam + " ");
                            Toast.makeText(MainActivity.this, "Győztél!", Toast.LENGTH_SHORT).show();
                        } else {
                            gepaltal_vsz.setImageResource(R.drawable.scissors);
                        }
                    } else if(e_eredmeny_szam == 3 || g_eredmeny_szam == 3){
                        showJatekVege();
                    }
                } catch (Exception e){
                    Log.e("Myapp", "Hiba történt");
                }

            }
        });
    }
    public void showJatekVege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(e_eredmeny_szam > g_eredmeny_szam){
            builder.setTitle("Győzelem!");
        } else{
            builder.setTitle("Vereség!");
        }

        builder.setMessage("Szeretne új játékot játszani?");
        builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
        builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void init(){
        ko_btn = findViewById(R.id.ko_btn);
        papir_btn = findViewById(R.id.papir_btn);
        ollo_btn = findViewById(R.id.ollo_btn);
        ember_valasztott = "";
        gep_valasztott = "";
        e_eredmeny = findViewById(R.id.ember_txt);
        g_eredmeny = findViewById(R.id.gep_txt);
        altalad_vsz = findViewById(R.id.altalad_vsz);
        gepaltal_vsz = findViewById(R.id.gepaltal_vsz);
        altalad_vsz.setImageResource(R.drawable.rock);
        gepaltal_vsz.setImageResource(R.drawable.rock);
        e_eredmeny_szam = 0;
        g_eredmeny_szam = 0;
        e_eredmeny.setText("Ember: 0 ");
        g_eredmeny.setText("Gép: 0");

    }
}