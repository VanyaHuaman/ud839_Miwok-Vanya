package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("Where are you going?","What is your name?",
                "My name is...","How are you feeling?","I’m feeling good.","Are you coming?",
                "Yes, I’m coming.","I’m coming.","Let’s go.","Come here.");

        List<String> miwokWords = Arrays.asList("minto wuksus","tinnә oyaase'nә","oyaaset...",
                "michәksәs?","kuchi achit","әәnәs'aa?","hәә’ әәnәm","әәnәm","yoowutis","әnni'nem");



        ArrayList<Word> words = new ArrayList<Word>();

        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index)));
        }

        //Creates an Array Adapter
        //simple_list_item_1 is a predefined android R.layout
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_phrases);

        //Stores the List View in a variable
        ListView listView = (ListView) findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

    }
}
