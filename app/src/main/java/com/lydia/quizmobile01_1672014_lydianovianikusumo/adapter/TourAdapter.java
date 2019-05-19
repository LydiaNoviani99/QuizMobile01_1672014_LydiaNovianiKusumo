package com.lydia.quizmobile01_1672014_lydianovianikusumo.adapter;

/**
 * @author 1672014 - Lydia Noviani Kusumo
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lydia.quizmobile01_1672014_lydianovianikusumo.R;
import com.lydia.quizmobile01_1672014_lydianovianikusumo.entity.Tours;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {

    private ArrayList<Tours> tours;
    private DataClickedListener listener;

    public ArrayList<Tours> getTours() {
        if(tours == null){
            tours = new ArrayList<>();
        }
        return tours;
    }

    public void setTours(ArrayList<Tours> tours) {
        this.getTours().clear();
        this.getTours().addAll(tours);
        notifyDataSetChanged();
    }

    public void setListener(DataClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        final Tours tours = getTours().get(position);
        holder.txtPlaceName.setText(String.valueOf(tours.getName()));
        holder.txtAddress.setText(String.valueOf(tours.getAddress()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTourClickedListener(tours);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTours().size();
    }

    class TourViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_placeName)
        TextView txtPlaceName;
        @BindView(R.id.tv_address)
        TextView txtAddress;

        public TourViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface DataClickedListener{
        void onTourClickedListener(Tours tours);
    }
}
