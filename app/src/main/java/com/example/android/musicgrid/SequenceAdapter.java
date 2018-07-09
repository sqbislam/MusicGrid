package com.example.android.musicgrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SequenceAdapter extends BaseAdapter {

    ArrayList<sequenceObject> list; // array of sequence objects
    Context c;   //base context
    AudioRecorder aud;

    public SequenceAdapter(ArrayList<sequenceObject> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        sequenceObject sequence = (sequenceObject) getItem(i);
        if(view==null){
            view= LayoutInflater.from(c).inflate(R.layout.sequence_griditem,viewGroup,false);

        }


        aud = new AudioRecorder(c,false);



        ImageButton btn = view.findViewById(R.id.image_btn);
        TextView text = view.findViewById(R.id.textString);

        text.setText(sequence.getName());
        final String clicked = "Clicked "+i;
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c,clicked,Toast.LENGTH_SHORT).show();
                }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                    view.setSelected(false);
                    aud.stopRecording();
                }
                else{
                    view.setSelected(true);
                    aud.startRecording(i);
                }

            }
        });


        return view;
    }

}
