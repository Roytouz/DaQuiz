package com.example.eksannara.daquiz;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ek Sannara on 2/18/2016.
 */

class CustomAdapter extends ArrayAdapter<String> {

    private final int[] imgs;
   public CustomAdapter(Context context, String[] topics, int[] imgs)
    {

        super(context,R.layout.custom_row,topics);
        this.imgs = imgs;
    }

@Override
    public View getView(int position,View convertView,ViewGroup parent)
{
    LayoutInflater narasInflator=LayoutInflater.from(getContext());
    View customView= narasInflator.inflate(R.layout.custom_row, parent, false);

    String singleItem=getItem(position);
    TextView narsText=(TextView) customView.findViewById(R.id.naratext);
    ImageView narasImage= (ImageView) customView.findViewById(R.id.naraImage);

    narsText.setText(singleItem);
    narasImage.setImageResource(imgs[position]);
    return customView;
}

}
