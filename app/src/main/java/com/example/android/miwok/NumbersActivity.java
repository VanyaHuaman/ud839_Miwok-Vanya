package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    ArrayList<Word> words = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("One","Two","Three","Four","Five","Six",
                                                         "Seven","Eight","Nine","Ten");

        List<String> miwokWords = Arrays.asList("Lutti","Otiiko","Tolookosu","Oyyisa","Massokka","Temmokka",
                "Kenekaku","Kawinta","Wo'e","Na'aacha");

        List<Integer> numberImages = Arrays.asList(R.drawable.number_one,R.drawable.number_two,
                R.drawable.number_three,R.drawable.number_four,R.drawable.number_five,
                R.drawable.number_six,R.drawable.number_seven,R.drawable.number_eight,
                R.drawable.number_nine,R.drawable.number_ten);

        List<Integer> soundFiles = Arrays.asList(R.raw.number_one,R.raw.number_two,R.raw.number_three,
                R.raw.number_four,R.raw.number_five,R.raw.number_six,R.raw.number_seven,
                R.raw.number_eight,R.raw.number_nine,R.raw.number_ten);



        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index),numberImages.get(index)
                    ,soundFiles.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentSongID = words.get(position).getSoundID();
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this,currentSongID);
                if(words.get(position).hasSound()) {
                    mMediaPlayer.start();
                }
            }
        });
    }




}


