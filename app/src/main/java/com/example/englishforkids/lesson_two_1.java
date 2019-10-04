package com.example.englishforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Random;

public class lesson_two_1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ArrayList<Word> arrayList;
    private Button RBtn;
    private Button LBtn;
    private ImageView WordImage;
    private ImageView AudioImage;
    private int currWord=-1;
    private MediaPlayer mediaPlayer;
    private TextView tvWrong;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK||i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                mediaPlayer.pause();
            }
            else
            {
                releaseMedia();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_one);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        currWord=MainActivity.pref.getInt(MainActivity.currWord,0);

        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);

        arrayList = new ArrayList<Word>();
        arrayList.add(new Word(getString(R.string.a_bee),getString(R.string.e_bee),R.drawable.father,R.raw.bee));
        arrayList.add(new Word(getString(R.string.a_chicken),getString(R.string.e_chicken),R.drawable.mother,R.raw.chicken_cluck_single));
        arrayList.add(new Word(getString(R.string.a_bear),getString(R.string.e_bear),R.drawable.grandmother,R.raw.bear));
        arrayList.add(new Word(getString(R.string.a_dog),getString(R.string.e_dog),R.drawable.grandfather,R.raw.dog));
        arrayList.add(new Word(getString(R.string.a_duck),getString(R.string.e_duck),R.drawable.son1,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_bee),getString(R.string.e_bee),R.drawable.father,R.raw.bee));
        arrayList.add(new Word(getString(R.string.a_bear),getString(R.string.e_bear),R.drawable.grandmother,R.raw.bear));
        arrayList.add(new Word(getString(R.string.a_chicken),getString(R.string.e_chicken),R.drawable.mother,R.raw.chicken_cluck_single));
        arrayList.add(new Word(getString(R.string.a_whale),getString(R.string.e_whale),R.drawable.brother1,R.raw.whale));
        arrayList.add(new Word(getString(R.string.a_elephant),getString(R.string.e_elephant),R.drawable.duaghter,R.raw.elephant));
        arrayList.add(new Word(getString(R.string.a_sheep),getString(R.string.e_sheep),R.drawable.sister1,R.raw.sheep));
        arrayList.add(new Word(getString(R.string.a_whale),getString(R.string.e_whale),R.drawable.brother1,R.raw.whale));

        RBtn=findViewById(R.id.rightWord);
        LBtn=findViewById(R.id.leftWord);
        tvWrong=findViewById(R.id.tvWrong);
        WordImage=findViewById(R.id.mainImage);
        AudioImage=findViewById(R.id.playAudio);

        tvWrong.setVisibility(View.INVISIBLE);

        RBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMedia();
                int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    if(RBtn.getText().toString().toLowerCase().matches(arrayList.get(currWord).getWord().toLowerCase())){
                        tvWrong.setVisibility(View.INVISIBLE);
                        mediaPlayer = MediaPlayer.create(lesson_two_1.this, R.raw.yahoo);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMedia();
                                showNextWord();
                                tvWrong.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    else{
                        tvWrong.setVisibility(View.VISIBLE);
                        mediaPlayer = MediaPlayer.create(lesson_two_1.this, R.raw.wrong_answer);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMedia();
                                tvWrong.setVisibility(View.INVISIBLE);
                            }
                        });
                    }



                }
            }
        });
        LBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMedia();
                int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    if(LBtn.getText().toString().toLowerCase().matches(arrayList.get(currWord).getWord().toLowerCase())){
                        tvWrong.setVisibility(View.INVISIBLE);
                        mediaPlayer = MediaPlayer.create(lesson_two_1.this,R.raw.yahoo);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMedia();
                                showNextWord();
                                tvWrong.setVisibility(View.INVISIBLE);
                            }
                        });

                    }
                    else{
                        tvWrong.setVisibility(View.VISIBLE);
                        mediaPlayer = MediaPlayer.create(lesson_two_1.this, R.raw.wrong_answer);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMedia();
                                tvWrong.setVisibility(View.INVISIBLE);
                            }
                        });
                    }




                }


            }
        });
        AudioImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playCurrWord();
            }
        });
        WordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playCurrWord();
            }
        });
        showNextWord();
    }
    private void releaseMedia()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
    private void playCurrWord()
    {
        releaseMedia();
        int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer = MediaPlayer.create(lesson_two_1.this,arrayList.get(currWord).getAudioId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMedia();
                }
            });
        }
    }
    private AlertDialog.Builder alertDialog;
    private void congratsTheBoy(){

        MainActivity.pref.edit().putInt(MainActivity.strCurrLevel,2).apply();
        alertDialog=new AlertDialog.Builder(this);
        alertDialog.create();
        alertDialog.setTitle(R.string.congrats);
        alertDialog.setMessage(R.string.congratsMessage);
        alertDialog.setPositiveButton(R.string.nextLevel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                startActivity(new Intent(lesson_two_1.this,MainActivity.class));
            }
        });
        alertDialog.setNegativeButton(R.string.startAgain, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currWord=-1;
                showNextWord();
            }
        });
        alertDialog.show();
    }

    private void showNextWord(){
        releaseMedia();
        currWord++;
        if(currWord>=arrayList.size())
        {
            congratsTheBoy();
            return;
        }
        WordImage.setImageResource(arrayList.get(currWord).getImageId());
        int random=new Random().nextInt(2);
        if(random==0)
        {
            LBtn.setText(arrayList.get(currWord).getWord());
            RBtn.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
        }
        else{
            RBtn.setText(arrayList.get(currWord).getWord());
            LBtn.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
        }
        TextView textView=findViewById(R.id.arabicWord);
        textView.setText(arrayList.get(currWord).getArabicWord());
        playCurrWord();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.level_nav_1:
                startActivity(new Intent(lesson_two_1.this, lesson_two_1.class));
                Toast.makeText(lesson_two_1.this,R.string.level_1,Toast.LENGTH_SHORT).show();
                break;

            case R.id.level_nav_2:
                startActivity(new Intent(lesson_two_1.this, lesson_two_2.class));
                Toast.makeText(lesson_two_1.this,R.string.level_2,Toast.LENGTH_SHORT).show();
                break;

            case R.id.level_nav_3:
                startActivity(new Intent(lesson_two_1.this, lesson_two_1.class));
                Toast.makeText(lesson_two_1.this,R.string.level_3,Toast.LENGTH_SHORT).show();
                break;

            case R.id.level_nav_4:
                startActivity(new Intent(lesson_two_1.this, lesson_two_2.class));
                Toast.makeText(lesson_two_1.this,R.string.level_4,Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainActivity.pref.edit().putInt(MainActivity.currWord,currWord).apply();
        releaseMedia();
    }
}
