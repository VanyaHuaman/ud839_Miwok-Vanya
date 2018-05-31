package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    final ArrayList<Word> words = new ArrayList<Word>();
    AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAfChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Audio Manager Object
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter adapter = new WordAdapter(getActivity(), words,R.color.category_colors);

        //Stores the List View in a variable
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //Connects the adapter to the ListView Variable and passes the
        // english Words Array to the adapter
        listView.setAdapter(adapter);

        //On CLick listener for the list View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentSongID = words.get(position).getSoundID();

                //Releases the Media Player if it currently exists because we are about to
                //play a different sound file
                releaseMediaPlayer();

                //Requests audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAfChangeListener,AudioManager.
                        STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //We have Audio Focus now

                    mMediaPlayer = MediaPlayer.create(getActivity(),currentSongID);

                    if(words.get(position).hasSound()) {
                        mMediaPlayer.start();
                    }
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });

                }
            }
        });

        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAfChangeListener);
        }
    }
}
