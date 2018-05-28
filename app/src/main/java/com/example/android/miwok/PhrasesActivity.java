package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    ArrayList<Word> words = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("Where are you going?","What is your name?",
                "My name is...","How are you feeling?","I’m feeling good.","Are you coming?",
                "Yes, I’m coming.","I’m coming.","Let’s go.","Come here.");

        List<String> miwokWords = Arrays.asList("minto wuksus","tinnә oyaase'nә","oyaaset...",
                "michәksәs?","kuchi achit","әәnәs'aa?","hәә’ әәnәm","әәnәm","yoowutis","әnni'nem");

        List<Integer> soundFiles = Arrays.asList(R.raw.phrase_where_are_you_going,
                R.raw.phrase_what_is_your_name,R.raw.phrase_my_name_is,
                R.raw.phrase_how_are_you_feeling,R.raw.phrase_im_feeling_good,
                R.raw.phrase_are_you_coming,R.raw.phrase_yes_im_coming,R.raw.phrase_lets_go);


        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index),-1,
                    soundFiles.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_phrases);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentSongID = words.get(position).getSoundID();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,currentSongID);
                if(words.get(position).hasSound()) {
                    mMediaPlayer.start();
                }
            }
        });

    }
}
