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
public class NumbersFragment extends Fragment {
    //Class Variables
    MediaPlayer mMediaPlayer;
    ArrayList<Word> words = new ArrayList<Word>();
    AudioManager mAudioManager;
    //creates a class On Audio Change Listener
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

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Audio Manager Object
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Default word Array
        List<String> defaultWords = Arrays.asList("One","Two","Three","Four","Five","Six",
                "Seven","Eight","Nine","Ten");
        //Miwok word Array
        List<String> miwokWords = Arrays.asList("Lutti","Otiiko","Tolookosu","Oyyisa","Massokka","Temmokka",
                "Kenekaku","Kawinta","Wo'e","Na'aacha");

        //Image Array
        List<Integer> numberImages = Arrays.asList(R.drawable.number_one,R.drawable.number_two,
                R.drawable.number_three,R.drawable.number_four,R.drawable.number_five,
                R.drawable.number_six,R.drawable.number_seven,R.drawable.number_eight,
                R.drawable.number_nine,R.drawable.number_ten);

        //Audio File Array
        List<Integer> soundFiles = Arrays.asList(R.raw.number_one,R.raw.number_two,R.raw.number_three,
                R.raw.number_four,R.raw.number_five,R.raw.number_six,R.raw.number_seven,
                R.raw.number_eight,R.raw.number_nine,R.raw.number_ten);

        //the Word objects Array
        for(int index=0;index<defaultWords.size();index++) {
            words.add(new Word(defaultWords.get(index),miwokWords.get(index),numberImages.get(index)
                    ,soundFiles.get(index)));
        }

        //Creates an Array Adapter
        WordAdapter adapter = new WordAdapter(getActivity(), words,R.color.category_numbers);

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
        //Releases the Audio Resources
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAfChangeListener);
        }
    }

}
