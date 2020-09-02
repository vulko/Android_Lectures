package com.kvolkov.androidlectures;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<String> mItems = new LinkedList<>();

    public void addItem(String item) {
        mItems.add(item);
        notifyItemInserted(mItems.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder vh = (ViewHolder) holder;
        vh.textView.setText(mItems.get(position));
        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(this.getClass().getName(), "Clicked on " + vh.textView.getText().toString());
                final int num = mItems.size() + 1;
                addItem("new item " + num);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_item);
            button = itemView.findViewById(R.id.btn);
        }
    }

}
