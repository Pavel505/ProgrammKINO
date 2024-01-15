package com.example.progkino.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Eventum;

import java.util.List;

public class EventumAdapter extends RecyclerView.Adapter<EventumAdapter.EventumViewHolder> {
    Context context;
    List<Eventum> eventumes;
    @NonNull
    @Override // Lbpfqy
    public EventumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override //Что подставляем дизайн
    public void onBindViewHolder(@NonNull EventumViewHolder holder, int position) {

    }

    @Override // С какими элементами будем работать
    public int getItemCount() {
        return eventumes.size();
    }

    public static final class EventumViewHolder extends RecyclerView.ViewHolder{

        public EventumViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
