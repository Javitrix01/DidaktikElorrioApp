package com.elorrieta.didaktikelorrioapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.elorrieta.didaktikelorrioapp.Class.Cards;
import com.elorrieta.didaktikelorrioapp.Class.DataBase;
import com.elorrieta.didaktikelorrioapp.Class.SlicingImage;
import com.elorrieta.didaktikelorrioapp.Class.Sound;

public class GameActivity9 extends AppCompatActivity {

    /**
     * Actividad del puzzle, cada vez que mueve una pieza se le suma un movimiento. Para no hacerlo frustrante si realiza
     * 200 movimientos o mas se le muestra un boton con el que puede pasar el puzzle directamente. Ademas, a forme de desarrollador
     * si se le hace click 10 veces al icono de sonido tambien se muestra el boton para pasar el puzzle.
     */

    private final int N = 3;
    Cards cards;

    private ImageButton[][] button;
    private final int[][] BUTTON_ID = {{R.id.b900, R.id.b901, R.id.b902},
            {R.id.b910, R.id.b911, R.id.b912},
            {R.id.b920, R.id.b921, R.id.b922}};
    private final int[] CARDS_ID = {R.drawable.card900, R.drawable.mapaelorriopieza1, R.drawable.mapaelorriopieza2,
            R.drawable.mapaelorriopieza3, R.drawable.mapaelorriopieza4, R.drawable.mapaelorriopieza5,
            R.drawable.mapaelorriopieza6, R.drawable.mapaelorriopieza7, R.drawable.mapaelorriopieza8};

    private String whatToShow;
    private TextView scoreTV;
    private int numOfSteps;
    private TextView recordTV;
    private int recordSteps;
    private ImageButton soundBtn;
    private boolean check;

    //variable para el control de la opcion secreta para pasar el puzzle
    private int contador = 0;

    DataBase dataBase = new DataBase(this);
    Sound sound=new Sound();

    @Override
    protected void onStart() {
        super.onStart();
        if (Sound.gameMusic != null) {
            if (!Sound.gameMusic.isPlaying())
                sound.switchMusic(Sound.gameMusic, Sound.backgroundMusic);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(!Sound.activitySwitchFlag)
            Sound.gameMusic.pause();
        else
            sound.switchMusic(Sound.backgroundMusic,Sound.gameMusic);
        Sound.activitySwitchFlag = false;

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Sound.activitySwitchFlag=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase.setPrefRef("PRESNAME9", "PRESSCORE9");
        setContentView(R.layout.activity_game9);

        //oculta la barra de arriba
        if (getSupportActionBar() != null)
        getSupportActionBar().hide();

        //BOTON PASAR PUZZLE
        Button botonPasar = findViewById(R.id.button3);
        botonPasar.setClickable(false);
        botonPasar.setVisibility(View.INVISIBLE);

        whatToShow = getIntent().getStringExtra("whatToShow");


        button = new ImageButton[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                button[i][j] = findViewById(BUTTON_ID[i][j]);
                button[i][j].setOnClickListener(onClickListener);
            }

        soundBtn = findViewById(R.id.bSoundOffOn9);
        ImageButton newGameBtn = findViewById(R.id.bNewGame9);
        ImageButton backBtn = findViewById(R.id.bBackMenu9);
        scoreTV = findViewById(R.id.tScore9);
        recordTV = findViewById(R.id.tBestScore9);

        newGameBtn.setOnClickListener(navigateBtnsClickListener);
        backBtn.setOnClickListener(navigateBtnsClickListener);
        soundBtn.setOnClickListener(navigateBtnsClickListener);
        if (whatToShow != null) {
            if (Sound.check)
                soundBtn.setImageResource(R.drawable.soundon);

            cards = new Cards(N, N);

            newGame();

        }
    }
    View.OnClickListener navigateBtnsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.bNewGame9:
                    Sound.menuClickSound.start();
                    newGame();
                    break;
                case R.id.bBackMenu9:
                    Sound.activitySwitchFlag=true;
                    Sound.menuClickSound.start();
                    backMenu();
                    break;
                case R.id.bSoundOffOn9:
                    soundOffOn();
                    Sound.menuClickSound.start();
                    break;

                default:
                    break;
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!check) {
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++)
                        if (v.getId() == BUTTON_ID[i][j])
                            buttonFunction(i, j);
            }
            else
                Toast.makeText(GameActivity9.this, R.string.you_won_toast, Toast.LENGTH_SHORT).show();

        }
    };

    public void buttonFunction(int row, int column) {
        if (cards != null) {
            cards.moveCards(row, column); // volver a cargar el puzzle
            if (cards.resultMove()) { // si los movimientos esta correctos
                Sound.buttonGameSound.start();
                numOfSteps++;
                showGame(); // poner las imagenes en los botones
                checkFinish();

                //si se llega a 200 intentos deja que se salte el puzzle
                if (numOfSteps >= 200) {
                    //si pasa de un numero de pasos le permite pasar el puzzle
                    Button botonPasar = findViewById(R.id.button3);
                    botonPasar.setVisibility(View.VISIBLE);
                    botonPasar.setClickable(true);

                    botonPasar.setOnClickListener(view -> startActivity(new Intent(GameActivity9.this, parada1_presentacion.class)));
                }
            }
        }
    }
    public void newGame() {
        cards.getNewCards();
        numOfSteps = 0;
        recordSteps=dataBase.getMaxScore(1,"PRESSCORE9");
        recordTV.setText(Integer.toString(recordSteps));
        showGame();
        check = false;
    }

    public void backMenu() {
        finish();
    }

    public void showGame() {
        scoreTV.setText(Integer.toString(numOfSteps));
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                if(whatToShow.equals("Zoo")){
                    if (cards.getValueBoard(i, j) != 0)
                        button[i][j].setImageBitmap(SlicingImage.imageChunksStorageList.get(cards.getValueBoard(i, j)));
                    else
                        button[i][j].setImageResource(CARDS_ID[cards.getValueBoard(i, j)]);}
                else
                button[i][j].setImageResource(CARDS_ID[cards.getValueBoard(i, j)]);

            }

    }

    public void checkFinish(){
        if(cards.finished(N, N)){
            showGame();
            Sound.winningSound.start();
            startActivity(new Intent(GameActivity9.this, parada1_presentacion.class));
            //openDialog();
            if ((numOfSteps < recordSteps) || (recordSteps == 0)) {
                recordTV.setText(Integer.toString(numOfSteps));
            }
            check = true;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }

    public void soundOffOn(){
        //contador para la opcion secreta para pasar el puzzle
        if(Sound.check)
        {
            Sound.check=false;
            soundBtn.setImageResource(R.drawable.soundoff);
        }
        else
        {
            Sound.check=true;
            soundBtn.setImageResource(R.drawable.soundon);
        }

        //si se hace click 10 veces en el cambio de sonido te muestra el boton para pasar el puzzle
        contador++;
        if (contador >= 10) {
            //si pasa de un numero de pasos le permite pasar el puzzle
            Button botonPasar = findViewById(R.id.button3);
            botonPasar.setVisibility(View.VISIBLE);
            botonPasar.setClickable(true);

            botonPasar.setOnClickListener(view -> startActivity(new Intent(GameActivity9.this, MenuPrincipalMapa.class)));
        }

        sound.setSounds();
        sound.setMusic();
    }


}

