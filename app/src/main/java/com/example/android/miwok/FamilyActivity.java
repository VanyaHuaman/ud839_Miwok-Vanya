package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    ArrayList<Word> words = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("father","mother","son","daughter","older brother"
                                        ,"younger brother","older sister","younger sister",
                                        "grandmother","grandfather");

        List<String> miwokWords = Arrays.asList("әpә","әṭa","angsi","tune","taachi","chalitti",
                "teṭe","kolliti","ama","paapa");

        List<Integer> familyImages = Arrays.asList(R.drawable.family_father,
                R.drawable.family_mother,R.drawable.family_son,R.drawable.family_daughter,
                R.drawable.family_older_brother,R.drawable.family_younger_brother,
                R.drawable.family_older_sister,R.drawable.family_younger_sister,
                R.drawable.family_grandmother,R.drawable.family_grandfather);

        List<Integer> soundFiles = Arrays.asList(R.raw.family_father,R.raw.family_mother,
                R.raw.family_son,R.raw.family_daughter,R.raw.family_older_brother,
                R.raw.family_younger_brother,R.raw.family_older_sister,R.raw.family_younger_sister,
                R.raw.family_grandmother,R.raw.family_grandfather);





        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index),familyImages.get(index),
                    soundFiles.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentSongID = words.get(position).getSoundID();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this,currentSongID);
                if(words.get(position).hasSound()) {
                    mMediaPlayer.start();
                }
            }
        });


    }
}
