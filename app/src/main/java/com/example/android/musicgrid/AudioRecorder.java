package com.example.android.musicgrid;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class AudioRecorder {

    private static final String LOG_TAG = "AudioRecorder";

    boolean check;

    private static Context context;

    private static String[] mFileName = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;




    public AudioRecorder(Context c, boolean check) {
        this.check = check;
        mFileName = new String[24];
        context = c;


    }



    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName[0]);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }


    public void startRecording(int i) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        setFileName(i);
        mRecorder.setOutputFile(mFileName[i]);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        makeToast("Recording...");
        mRecorder.start();
    }

    private void setFileName(int i) {
        mFileName[i]=context.getExternalCacheDir().getAbsolutePath();
        mFileName[i] += "/audiorecordtest "+i+".3gp";
    }

    public void stopRecording() {
        mRecorder.stop();
        makeToast("Stopped Recording");
        mRecorder.release();
        mRecorder = null;
    }


    private void makeToast(String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }


}
