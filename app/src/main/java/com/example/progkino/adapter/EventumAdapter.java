package com.example.progkino.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Eventum;
import com.example.progkino.R;

import java.util.List;

public class EventumAdapter extends RecyclerView.Adapter<EventumAdapter.EventumViewHolder> {
    Context context;
    List<Eventum> eventumes;

    public EventumAdapter(Context context, List<Eventum> eventumes) {
        this.context = context;
        this.eventumes = eventumes;
    }

    @NonNull
    @Override // Lbpfqy
    public EventumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventumItems = LayoutInflater.from(context).inflate(R.layout.eventum_item, parent , false);
        return new EventumAdapter.EventumViewHolder(eventumItems);
    }

    @Override //Что подставляем дизайн
    public void onBindViewHolder(@NonNull EventumViewHolder holder, int position) {
        holder.eventumBg.setBackgroundColor(Color.parseColor(eventumes.get(position).getColor()));
        int imageId = context.getResources().getIdentifier( eventumes.get(position).getImg(),"drawable",context.getPackageName());
        holder.eventumImage.setImageResource(imageId);

        holder.eventumTitle.setText(eventumes.get(position).getTitle());
        holder.eventumDate.setText((CharSequence) eventumes.get(position).getDateEventum());
        holder.eventumType.setText(eventumes.get(position).getType());
    }

    @Override // С какими элементами будем работать
    public int getItemCount() {
        return eventumes.size();
    }

    public static final class EventumViewHolder extends RecyclerView.ViewHolder{

        LinearLayout eventumBg;
        ImageView eventumImage;
        TextView eventumTitle, eventumDate, eventumType;
        public EventumViewHolder(@NonNull View itemView) {
            super(itemView);

            eventumBg = itemView.findViewById(R.id.eventumBg);
            eventumImage = itemView.findViewById(R.id.eventumImage);
            eventumTitle = itemView.findViewById(R.id.eventumTitle);
            eventumDate = itemView.findViewById(R.id.eventumDate);
            eventumType = itemView.findViewById(R.id.eventumType);
        }
    }


}
