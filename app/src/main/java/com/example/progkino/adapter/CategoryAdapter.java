package com.example.progkino.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.HomeActivity;
import com.example.progkino.Models.Category;
import com.example.progkino.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Использование определенного дизайна через описание вложенного класса CategoryViewHolder
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent , false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
// Что мы будем подставлять в конкретное поле

        holder.categoryTitle.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Изначально HomeActivity.showEventumesByCategory(categories.get(position).getId());
                int proba = categories.get(position).getId();
                String per;
                switch (proba) {
                    case  (1):
                        per = "Интеллект";
                        break;
                    case (2):
                        per = "Настолки";
                        break;
                    case (3):
                        per = "Сюжетки";
                        break;
                    case (4):
                        per = "Турниры";
                        break;
                    default:
                        per = "Обновить";
                        break;
                }
                HomeActivity.showEventumesByCategory(per);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
