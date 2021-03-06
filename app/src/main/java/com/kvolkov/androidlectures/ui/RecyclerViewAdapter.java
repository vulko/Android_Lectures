package com.kvolkov.androidlectures.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kvolkov.androidlectures.R;

import java.util.LinkedList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    public RecyclerViewAdapter(RecyclerView view) {
        mView = view;
    }

    private List<String> mItems = new LinkedList<>();
    private RecyclerView mView;

    public void addItem(String item, int ind) {
        Log.e(this.getClass().getName(), "addItem, POS = " + ind);
        mItems.add(ind, item);
        notifyItemInserted(ind);
        notifyItemRangeChanged(ind + 1, mItems.size() - (ind + 1));
    }

    public void setItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int ind) {
        Log.e(this.getClass().getName(), "removeItem, POS = " + ind);
        mItems.remove(ind);
        notifyItemRemoved(ind);
        notifyItemRangeChanged(ind, mItems.size() - (ind));
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
        vh.pos = position;
        Log.e(this.getClass().getName(), "onBindViewHolder, POS = " + vh.pos);
        vh.textView.setText(mItems.get(position) + " " + position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private ViewHolder getViewHolderAt(int idx) {
        return ((ViewHolder) mView.findViewHolderForAdapterPosition(idx));
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.e(this.getClass().getName(), "onViewAttachedToWindow, POS = " + ((ViewHolder) holder).pos);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(this.getClass().getName(), "onViewDetachedFromWindow, POS = " + ((ViewHolder) holder).pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        int pos;
        TextView textView;
//        Button button;
//        Button buttonDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_item);
//            button = itemView.findViewById(R.id.btn);
//            buttonDel = itemView.findViewById(R.id.btnDel);
        }
    }

}
