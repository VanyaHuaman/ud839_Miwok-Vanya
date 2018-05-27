package com.example.android.miwok;

import android.content.Context;

/*
    Creates a object with the default language string and a translation string
 */


public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;

    //Constructor
    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    //Returns the Default translation String
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    //Returns the Miwok translation String
    public  String getMiwokTranslation(){
        return mMiwokTranslation;
    }

}
