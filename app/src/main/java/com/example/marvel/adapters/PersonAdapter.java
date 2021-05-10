package com.example.marvel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.models.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> marvels ;
    Context context;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked (int index);
        void onPlayIconClicked(int index);
    }

    public PersonAdapter (Context context, ArrayList<Person> list ) {
        marvels = list;
        this.context = context;
        activity = (ItemClicked) context;
    }

    public ArrayList<Person> getMarvels() {
        return marvels;
    }

    public void setMarvels(ArrayList<Person> marvels) {
        this.marvels = marvels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView name ;
        TextView team;
        ImageView playIcon;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            team = itemView.findViewById(R.id.tvTeam);
            picture = itemView.findViewById(R.id.ivPic);
            playIcon = itemView.findViewById(R.id.playImageMain);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    activity.onItemClicked(marvels.indexOf((Person) v.getTag()));
                }
            });

            playIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onPlayIconClicked(marvels.indexOf((Person) v.getTag()));
                }
            });
        }
    }
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(marvels.get(position));
        holder.playIcon.setTag(marvels.get(position));

        holder.name.setText(marvels.get(position).getName());
        holder.team.setText(marvels.get(position).getTeam() );
        Picasso.with(context)
                .load(marvels.get(position).getImageURL())
                .placeholder(R.drawable.placeholder).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return marvels.size();
    }
}
