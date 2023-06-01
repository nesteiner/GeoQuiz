package com.example.geoquiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.geoquiz.R;
import com.example.geoquiz.models.Question;
import com.example.geoquiz.viewholder.CheckViewHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckViewHolder> {
    List<Question> data;

    public CheckAdapter(List<Question> data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_check, parent, false);

        return new CheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CheckViewHolder holder, int position) {
        holder.setTitle(position + 1 + ". " + data.get(position).textQuestion);
        holder.setFinish(data.get(position).finish);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
