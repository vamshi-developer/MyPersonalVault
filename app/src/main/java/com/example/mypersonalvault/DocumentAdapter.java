package com.example.mypersonalvault;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {

    List<DocumentModel> list;
    Context context;

    public DocumentAdapter(List<DocumentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView docName;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            docName = itemView.findViewById(R.id.docName);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

    @NonNull
    @Override
    public DocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.ViewHolder holder, int position) {

        DocumentModel model = list.get(position);
        holder.docName.setText(model.getName());

        // 🔥 OPEN FILE
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(model.getUrl()));
            context.startActivity(intent);
        });

        // ✏️ EDIT NAME
        holder.docName.setOnClickListener(v -> {
            EditText input = new EditText(context);
            input.setText(model.getName());

            new AlertDialog.Builder(context)
                    .setTitle("Edit Name")
                    .setView(input)
                    .setPositiveButton("Save", (d, w) -> {
                        model.setName(input.getText().toString());
                        notifyDataSetChanged();
                    })
                    .show();
        });

        // ❌ DELETE
        holder.deleteBtn.setOnClickListener(v -> {
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}