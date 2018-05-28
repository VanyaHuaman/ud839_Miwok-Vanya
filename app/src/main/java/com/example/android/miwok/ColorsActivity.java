package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    final ArrayList<Word> words = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("Red", "Green", "Brown", "Gray", "Black", "White",
                "Dusty Yellow", "Mustard Yellow");

        List<String> miwokWords = Arrays.asList("Wetetti", "Chokokki", "Takaakki", "Topoppi",
                "Kululli", "Kelelli", "Topiisә", "Chiwiiṭә");

        List<Integer> colorImages = Arrays.asList(R.drawable.color_red,R.drawable.color_green,
                R.drawable.color_brown,R.drawable.color_gray,R.drawable.color_black,
                R.drawable.color_white,R.drawable.color_dusty_yellow,R.drawable.color_mustard_yellow);

        List<Integer> soundFiles = Arrays.asList(R.raw.color_red,R.raw.color_green,R.raw.color_brown,
                R.raw.color_gray,R.raw.color_black,R.raw.color_white,R.raw.color_dusty_yellow,
                R.raw.color_mustard_yellow);



        for (int index = 0; index < defaultWords.size(); index++) {
            words.add(new Word(defaultWords.get(index), miwokWords.get(index),colorImages.get(index),
                    soundFiles.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentSongID = words.get(position).getSoundID();
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this,currentSongID);
                if(words.get(position).hasSound()) {
                    mMediaPlayer.start();
                }
            }
        });

    }
}
