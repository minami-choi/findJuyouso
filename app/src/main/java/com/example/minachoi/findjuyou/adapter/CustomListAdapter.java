package com.example.minachoi.findjuyou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minachoi.findjuyou.R;
import com.example.minachoi.findjuyou.fragment.ListFragment;
import com.example.minachoi.findjuyou.models.OIL;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> {

    List<OIL> oilList;
    Context context;
    ListFragment.SelectOil selectOil;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView distanceTextView;
        ImageView logoImageView;

        public MyViewHolder(View view) {
            super(view);
            this.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            this.priceTextView = (TextView) view.findViewById(R.id.priceTextView);
            this.distanceTextView = (TextView) view.findViewById(R.id.distanceTextView);
            this.logoImageView = (ImageView) view.findViewById(R.id.logoImageView);
        }
    }

    public CustomListAdapter(List<OIL> oilList, Context context) {
        this.oilList = oilList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        selectOil = (ListFragment.SelectOil) context;
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OIL oilObject = this.oilList.get(position);
        holder.nameTextView.setText(oilObject.getOSNM().toString());
        holder.distanceTextView.setText(oilObject.getDISTANCE().toString());
        holder.priceTextView.setText(oilObject.getPRICE().toString());

//        Log.e(TAG, "onBindViewHolder: " + oilObject.getPOLLDIVCD() );
        if(oilObject.getPOLLDIVCD().equals("GSC"))
            holder.logoImageView.setImageResource(R.drawable.gs);

        else if(oilObject.getPOLLDIVCD().equals("HDO"))
            holder.logoImageView.setImageResource(R.drawable.hyundai);

        else if(oilObject.getPOLLDIVCD().equals("SOL"))
            holder.logoImageView.setImageResource(R.drawable.s_oil);

        else if(oilObject.getPOLLDIVCD().equals("SKE"))
            holder.logoImageView.setImageResource(R.drawable.sk);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectOil.selectOilStation(oilObject);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.oilList.size();
    }
}
