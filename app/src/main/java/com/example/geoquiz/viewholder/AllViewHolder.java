package com.example.geoquiz.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.geoquiz.R;

public class AllViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView answer;

    public AllViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        answer = itemView.findViewById(R.id.answer);
    }

    public void setTitle(String text) {
        title.setText(text);
    }

    public void setAnswer(String text) {
        answer.setText(text);
    }
}
