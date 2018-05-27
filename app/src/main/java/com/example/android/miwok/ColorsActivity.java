package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("Red", "Green", "Brown", "Gray", "Black", "White",
                "Dusty Yellow", "Mustard Yellow");

        List<String> miwokWords = Arrays.asList("Wetetti", "Chokokki", "Takaakki", "Topoppi",
                "Kululli", "Kelelli", "Topiisә", "Chiwiiṭә");

        ArrayList<Word> words = new ArrayList<Word>();

        for (int index = 0; index < defaultWords.size(); index++) {
            words.add(new Word(defaultWords.get(index), miwokWords.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

    }
}
