package com.example.android.miwok;

import android.content.Context;
import android.graphics.drawable.Drawable;

/*
    Creates a object with the default language string and a translation string
 */


public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageID = NO_IMAGE_PROVIDED;
    private int mSoundID = NO_SOUND_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_SOUND_PROVIDED = -1;

    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }


    //Constructor
    public Word(String defaultTranslation, String miwokTranslation, int imageID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageID = imageID;
    }



    //Constructor
    public Word(String defaultTranslation, String miwokTranslation, int imageID, int soundID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        if(imageID==0){
            mImageID=NO_IMAGE_PROVIDED;
        }else {
            mImageID = imageID;
        }

        mSoundID = soundID;
    }



    //Returns the Default translation String
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    //Returns the Miwok translation String
    public  String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageID() {
        return mImageID;
    }

    public void setImageID(int imageID) {
        mImageID = imageID;
    }

    public boolean hasImage(){
        return mImageID != NO_IMAGE_PROVIDED;
    }

    public int getSoundID(){
        return mSoundID;
    }
    public boolean hasSound(){
        return mSoundID != NO_SOUND_PROVIDED;
    }

}
