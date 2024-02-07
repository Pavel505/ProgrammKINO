package com.example.progkino.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.EventumPage;
import com.example.progkino.Models.Eventum;
import com.example.progkino.R;

import java.util.List;

public class EventumAdapter extends RecyclerView.Adapter<EventumAdapter.EventumViewHolder> {
    Context context;
    List<Eventum> eventumesList;

    public EventumAdapter(Context context, List<Eventum> eventumesList) {
        this.context = context;
        this.eventumesList = eventumesList;
    }

    @NonNull
    @Override // Дизайн
    public EventumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventumItems = LayoutInflater.from(context).inflate(R.layout.eventum_item, parent , false);
        return new EventumAdapter.EventumViewHolder(eventumItems);
    }

    @SuppressLint("RecyclerView")
    @Override //Что подставляем дизайн
    public void onBindViewHolder(@NonNull EventumViewHolder holder, int position) {
        holder.eventumBg.setCardBackgroundColor(Color.parseColor(eventumesList.get(position).getColor()));
        int imageId = context.getResources().getIdentifier( eventumesList.get(position).getImg(),"drawable",context.getPackageName());
        holder.eventumImage.setImageResource(imageId);

        holder.eventumTitle.setText(eventumesList.get(position).getTitle());
        holder.eventumDate.setText((CharSequence) eventumesList.get(position).getDateEventum());
        holder.eventumType.setText(eventumesList.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventumPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)
                 context, new Pair<View,String>(holder.eventumImage,"eventumImage")
                        );
                // Dop значения
                intent.putExtra("eventumBg", Color.parseColor(eventumesList.get(position).getColor()));
                intent.putExtra("eventumImage",imageId);
                intent.putExtra("eventumTitle",eventumesList.get(position).getTitle());
                intent.putExtra("eventumDate",(CharSequence) eventumesList.get(position).getDateEventum());
                intent.putExtra("eventumType",eventumesList.get(position).getType());
                intent.putExtra("eventumDescription",eventumesList.get(position).getEventumDescription());

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override // С какими элементами будем работать
    public int getItemCount() {
        return eventumesList.size();
    }

    public static final class EventumViewHolder extends RecyclerView.ViewHolder{

        CardView eventumBg;
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
