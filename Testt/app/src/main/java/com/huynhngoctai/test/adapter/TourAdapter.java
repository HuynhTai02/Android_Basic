package com.huynhngoctai.test.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhngoctai.test.R;
import com.huynhngoctai.test.database.entity.Tour;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private List<Tour> tourList;
    private final IClickItemTour iClickItemTour;
    public interface IClickItemTour {
        void editTour(Tour tour);

        void deleteTour(Tour tour);
    }

    public TourAdapter(IClickItemTour iClickItemTour) {
        this.iClickItemTour = iClickItemTour;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Tour> list) {
        this.tourList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcvtour, parent, false);
        return new TourViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        final Tour tour = tourList.get(position);
        if (tour == null) {
            return;
        }
        holder.tvTourID.setText("Tour ID:"+String.valueOf(tour.getTourID()));
        holder.tvTourName.setText("Tour Name: "+tour.getTourName());
        holder.tvTourDescription.setText("Tour Description: "+tour.getTourDescription());
        holder.tvTourNumbOfGuests.setText("Number Of Guest" + String.valueOf(tour.getNumbOfGuests()));
        holder.tvSchedule.setText("Schedule: " + tour.getSchedule());
        holder.tvTourPrice.setText("Tour Price" + String.format("%.2f", tour.getTourPrice()));

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemTour.editTour(tour);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemTour.deleteTour(tour);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tourList != null) {
            return tourList.size();
        }
        return 0;
    }

    public static class TourViewHolder extends RecyclerView.ViewHolder {
        TextView tvTourID;
        TextView tvTourName;
        TextView tvTourPrice;
        TextView tvTourNumbOfGuests;
        TextView tvTourDescription;
        TextView tvSchedule;
        ImageButton btnEdit;
        ImageButton btnDelete;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTourID = itemView.findViewById(R.id.tvTourID);
            tvTourName = itemView.findViewById(R.id.tvTourName);
            tvTourDescription = itemView.findViewById(R.id.tvTourDescription);
            tvTourNumbOfGuests = itemView.findViewById(R.id.tvNumbOfGuest);
            tvSchedule = itemView.findViewById(R.id.tvSchedule);
            tvTourPrice = itemView.findViewById(R.id.tvTourPrice);

            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
