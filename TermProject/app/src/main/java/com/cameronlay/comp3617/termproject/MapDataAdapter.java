package com.cameronlay.comp3617.termproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MapDataAdapter extends RecyclerView.Adapter<MapDataAdapter.MapDataViewHolder> {


        class MapDataViewHolder extends RecyclerView.ViewHolder {
            private final TextView mapTitleView;
            private final TextView mapDescriptionView;

            private MapDataViewHolder(View itemView) {
                super(itemView);
                mapTitleView = itemView.findViewById(R.id.recyclerTitle);
                mapDescriptionView = itemView.findViewById(R.id.recyclerDesctiption);
            }
        }

        private final LayoutInflater mInflater;
        private List<String> mapTitles;
        private List<String> mapDescriptions;


        MapDataAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public MapDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new MapDataViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MapDataViewHolder holder, int position) {
            if (mapTitles != null && mapDescriptions != null) {
                String currentTitle = mapTitles.get(position);
                String currentDescription = mapDescriptions.get(position);
                holder.mapTitleView.setText(currentTitle);
                holder.mapDescriptionView.setText(currentDescription);
            } else {
                // Covers the case of data not being ready yet.
                holder.mapTitleView.setText("No Word");
            }
        }

        void setMapData(List<String> mTitles, List<String> mDescriptions){
            mapTitles = mTitles;
            mapDescriptions = mDescriptions;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (mapTitles != null)
                return mapTitles.size();
            else return 0;
        }
    }

