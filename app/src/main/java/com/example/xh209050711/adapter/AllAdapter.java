package com.example.xh209050711.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xh209050711.R;
import com.example.xh209050711.model.Question;
import com.example.xh209050711.viewholder.AllViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllViewHolder> {
    List<Question> data;

    public AllAdapter(List<Question> data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_all, parent, false);
        return new AllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AllViewHolder holder, int position) {
        holder.setTitle(position + 1 + ". " + data.get(position).textQuestion);
        holder.setAnswer(data.get(position).answerTrue ? "对" : "错");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
