package com.saikat.contactroom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.saikat.contactroom.R;
import com.saikat.contactroom.model.Contact;

import java.util.List;
import java.util.Objects;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    private OnContactClickListener contactClickListener;
    private List<Contact> contactlist;
    private Context context;

    public RecylerViewAdapter(List<Contact> contactlist, Context context, OnContactClickListener onContactClickListener) {
        this.contactlist = contactlist;
        this.context = context;
        this.contactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = Objects.requireNonNull(contactlist.get(position));
        holder.name.setText(contact.getName());
        holder.occupation.setText(contact.getOccupation());
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(contactlist.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView occupation;
        OnContactClickListener onContactClickListener;
        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.row_name_textview);
            occupation = itemView.findViewById(R.id.row_occupation_textview);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener {
        void onContactClick(int position);
    }
}
