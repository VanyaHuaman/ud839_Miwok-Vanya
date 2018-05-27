package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        List<String> defaultWords = Arrays.asList("One","Two","Three","Four","Five","Six",
                                                         "Seven","Eight","Nine","Ten");

        List<String> miwokWords = Arrays.asList("Lutti","Otiiko","Tolookosu","Oyyisa","Massokka","Temmokka",
                "Kenekaku","Kawinta","Wo'e","Na'aacha");

        ArrayList<Word> words = new ArrayList<Word>();

        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index)));
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


