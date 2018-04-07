package com.deepanshibansal.increasingvocab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepanshi Bansal on 06-04-2018.
 */

public class words extends RecyclerView.Adapter<words.ViewHolder>{
    List<String> word = new ArrayList<>();
    List<String> meaning = new ArrayList<>();
LayoutInflater inflater;
    public words(List<String> w,List<String> m) {
        word=w;
        meaning=m;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.vocab, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.wordtext.setText(word.get(position));
        holder.meaningtext.setText(meaning.get(position));

    }

    @Override
    public int getItemCount() {
        return word.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView wordtext;
        public TextView meaningtext;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
           wordtext = (TextView) v.findViewById(R.id.rvword);
           meaningtext = (TextView) v.findViewById(R.id.rvmeaning);

        }
    }
    public void add(int position, String item) {
        word.add(position, item);
        meaning.add(position, item);

        notifyItemInserted(position);
    }
}
