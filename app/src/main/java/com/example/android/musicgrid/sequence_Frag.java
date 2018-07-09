package com.example.android.musicgrid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class sequence_Frag extends Fragment {

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private String [] permissions = {Manifest.permission.RECORD_AUDIO};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.sequence_frag, container, false);

        GridView gridView= (GridView) rootView.findViewById(R.id.content);

        ArrayList<sequenceObject> list = new ArrayList<>();

        for (int i=0 ; i<18 ; i++) {
            list.add(new sequenceObject("Seq "+i));
        }

        SequenceAdapter adapter = new SequenceAdapter(list, getContext());
        gridView.setAdapter(adapter);


        return rootView;
    }

    public void changeColor(View view){
        ImageView imgview = (ImageView) view;
        imgview.setImageAlpha(0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) getActivity().finish();

    }

}
