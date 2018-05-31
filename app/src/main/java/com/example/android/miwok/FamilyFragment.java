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
public class FamilyFragment extends Fragment {

    MediaPlayer mMediaPlayer;
    ArrayList<Word> words = new ArrayList<Word>();
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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        //Audio Manager Object
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter adapter = new WordAdapter(getActivity(), words,R.color.category_family);

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
