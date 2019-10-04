package com.example.englishforkids;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class lesson_one_2 extends AppCompatActivity implements View.OnDragListener{
    TextView tv_1;
    TextView tv_2;
    TextView tv_3;
    TextView tv_4;
    ImageView WordImage;
    ImageView audioImage;
    String tagButton_1="tag1";
    String tagButton_2="tag2";
    String tagButton_3="tag3";
    String tagButton_4="tag4";
    private ArrayList<Word> arrayList;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private int currWord=-1;

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
        setContentView(R.layout.level_two);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_4 = findViewById(R.id.tv_4);
        WordImage=findViewById(R.id.mainImage);
        audioImage=findViewById(R.id.playAudio);


        tv_1.setTag(tagButton_1);
        tv_2.setTag(tagButton_2);
        tv_3.setTag(tagButton_3);
        tv_4.setTag(tagButton_4);


        LinearLayout linearLayout=findViewById(R.id.linearLayout);
        linearLayout.setOnDragListener(this);

        LinearLayout wordsLayout1=findViewById(R.id.wordsLayout1);
        wordsLayout1.setOnDragListener(this);

        setDragListener();
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
        arrayList = new ArrayList<Word>();
        arrayList.add(new Word(getString(R.string.a_duck),getString(R.string.e_duck),R.drawable.duck,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_dog),getString(R.string.e_dog),R.drawable.dog,R.raw.dog));
        arrayList.add(new Word(getString(R.string.a_bear),getString(R.string.e_bear),R.drawable.bear,R.raw.bear));
        //arrayList.add(new Word(getString(R.string.a_snake),getString(R.string.e_snake),R.mipmap.snake,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_elephant),getString(R.string.e_elephant),R.drawable.elephant,R.raw.elephant));
        arrayList.add(new Word(getString(R.string.a_sheep),getString(R.string.e_sheep),R.drawable.sheep,R.raw.sheep));
        arrayList.add(new Word(getString(R.string.a_bee),getString(R.string.e_bee),R.drawable.bee,R.raw.bee));
        //arrayList.add(new Word(getString(R.string.a_octopus),getString(R.string.e_octopus),R.mipmap.octopus,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_cat),getString(R.string.e_cat),R.drawable.cat,R.raw.cat));
        // arrayList.add(new Word(getString(R.string.a_flounder),getString(R.string.e_flounder),R.mipmap.flounder_fish,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_whale),getString(R.string.e_whale),R.drawable.whale,R.raw.whale));
        arrayList.add(new Word(getString(R.string.a_chicken),getString(R.string.e_chicken),R.drawable.chicken,R.raw.chicken_cluck_single));
        arrayList.add(new Word(getString(R.string.a_cat),getString(R.string.e_cat),R.drawable.cat,R.raw.cat));
        arrayList.add(new Word(getString(R.string.a_bear),getString(R.string.e_bear),R.drawable.bear,R.raw.bear));
        arrayList.add(new Word(getString(R.string.a_wolf),getString(R.string.e_wolf),R.drawable.wolf,R.raw.wolf));
        arrayList.add(new Word(getString(R.string.a_duck),getString(R.string.e_duck),R.drawable.duck,R.raw.duck));
        arrayList.add(new Word(getString(R.string.a_frog),getString(R.string.e_frog),R.drawable.frog,R.raw.frog));
        arrayList.add(new Word(getString(R.string.a_bear),getString(R.string.e_bear),R.drawable.bear,R.raw.bear));
        arrayList.add(new Word(getString(R.string.a_frog),getString(R.string.e_frog),R.drawable.frog,R.raw.frog));
        arrayList.add(new Word(getString(R.string.a_seaLion),getString(R.string.e_seaLion),R.drawable.sealion,R.raw.sea_lion));

        showNextWord();
        WordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMedia();
                playCurrWord();
            }
        });
        audioImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releaseMedia();
                playCurrWord();
            }
        });

    }
    private void congratsTheBoy(){

        AlertDialog.Builder alertDialog;
        MainActivity.pref.edit().putInt(MainActivity.strCurrLevel,3).apply();
        alertDialog=new AlertDialog.Builder(this);
        alertDialog.create();
        alertDialog.setTitle(R.string.congrats);
        alertDialog.setMessage(R.string.congratsMessage);
        alertDialog.setPositiveButton(R.string.nextLevel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                startActivity(new Intent(lesson_one_2.this,MainActivity.class));
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
        int result=audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer = MediaPlayer.create(lesson_one_2.this,arrayList.get(currWord).getAudioId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMedia();
                }
            });
        }
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
        int random=new Random().nextInt(4);
        if(random==0)
        {
            tv_1.setText(arrayList.get(currWord).getWord());
            tv_2.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
            tv_3.setText(arrayList.get((currWord+5)%(arrayList.size()-1)).getWord());
            tv_4.setText(arrayList.get((currWord+1)%(arrayList.size()-1)).getWord());

        }
        else if(random==1)
        {
            tv_1.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
            tv_2.setText(arrayList.get(currWord).getWord());
            tv_3.setText(arrayList.get((currWord+5)%(arrayList.size()-1)).getWord());
            tv_4.setText(arrayList.get((currWord+1)%(arrayList.size()-1)).getWord());

        }
        else if(random==2)
        {
            tv_1.setText(arrayList.get((currWord+5)%(arrayList.size()-1)).getWord());
            tv_2.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
            tv_3.setText(arrayList.get(currWord).getWord());
            tv_4.setText(arrayList.get((currWord+1)%(arrayList.size()-1)).getWord());

        }
        else
        {
            tv_1.setText(arrayList.get((currWord+1)%(arrayList.size()-1)).getWord());
            tv_2.setText(arrayList.get((currWord+3)%(arrayList.size()-1)).getWord());
            tv_3.setText(arrayList.get((currWord+5)%(arrayList.size()-1)).getWord());
            tv_4.setText(arrayList.get(currWord).getWord());

        }
        TextView textView=findViewById(R.id.arabicWord);
        textView.setText(arrayList.get(currWord).getArabicWord());
        playCurrWord();
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        switch (action)
        {
            case DragEvent.ACTION_DRAG_STARTED:
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.

                    //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view

                    // Invalidate the view to force a redraw in the new tint
                    //  view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                if(view==findViewById(R.id.linearLayout))
                {
                    view.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
                    view.invalidate();
                }
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                if(view==findViewById(R.id.linearLayout))
                {
                    view.getBackground().clearColorFilter();
                    view.invalidate();
                }

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                if(view==findViewById(R.id.linearLayout))
                {
                    view.getBackground().clearColorFilter();
                    view.invalidate();
                    return true;
                }
            case DragEvent.ACTION_DROP:
                final View v = (View) dragEvent.getLocalState();
                if(view!=findViewById(R.id.linearLayout))
                {
                    v.setVisibility(View.VISIBLE);
                    return true;
                }
                if(((TextView)v).getText().toString().matches(arrayList.get(currWord).getWord()))
                {
                    findViewById(R.id.wrong_tv).setVisibility(View.INVISIBLE);
                    view.getBackground().clearColorFilter();
                    view.invalidate();
                    final ViewGroup linearLayout = (ViewGroup) v.getParent();
                    linearLayout.removeView(v);
                    final LinearLayout newView = (LinearLayout) view;
                    newView.addView(v);
                    v.setVisibility(View.VISIBLE);
                    int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                    {
                        releaseMedia();
                        mediaPlayer=MediaPlayer.create(lesson_one_2.this,R.raw.yahoo);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                releaseMedia();
                                findViewById(R.id.wrong_tv).setVisibility(View.VISIBLE);
                                newView.removeView(v);
                                linearLayout.addView(v);
                                showNextWord();
                            }
                        });
                    }
                }
                else {
                    final TextView textView=(TextView)findViewById(R.id.wrong_tv);
                    textView.setText(R.string.wrong);
                    textView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        releaseMedia();
                        mediaPlayer = MediaPlayer.create(lesson_one_2.this, R.raw.wrong_answer);
                        mediaPlayer.start();
                        ((TextView)findViewById(R.id.wrong_tv)).setVisibility(View.VISIBLE);
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                textView.setText(R.string.drop_here);
                                textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                                releaseMedia();

                            }
                        });
                    }
                }

                return true;
        }


        return false;
    }

    private void setDragListener()
    {

        tv_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                ClipData data = new ClipData(view.getTag().toString(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tv_1);
                view.startDrag(data, myShadow, view, 0);
                view.setVisibility(View.INVISIBLE);
                return false;
            }
        });


        tv_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                ClipData data = new ClipData(view.getTag().toString(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tv_2);
                view.startDrag(data, myShadow, view, 0);
                view.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        tv_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                ClipData data = new ClipData(view.getTag().toString(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tv_3);
                view.startDrag(data, myShadow, view, 0);
                view.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        tv_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClipData.Item item = new ClipData.Item(view.getTag().toString());
                ClipData data = new ClipData(view.getTag().toString(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);

                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tv_4);
                view.startDrag(data, myShadow, view, 0);
                view.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();
    }
}
