package com.example.geoquiz.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.geoquiz.R;

public class CheckViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView isfinish;

    public CheckViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        isfinish = itemView.findViewById(R.id.isfinish);
    }

    public void setTitle(String text) {
        title.setText(text);
    }

    public void setFinish(boolean finish) {
        isfinish.setText(finish ? "已完成" : "未完成");
    }
}
