package com.wap.smartstay.Fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wap.smartstay.R;

import java.util.List;

public class SmartkeyFragmentAdapter extends RecyclerView.Adapter<SmartkeyFragmentAdapter.SmartkeyViewHolder> {
    private List<SmartkeyList> SmartkeyList;
    private Context context;

    public SmartkeyFragmentAdapter(List<SmartkeyList> SmartkeyList, Context context) {
        this.SmartkeyList = SmartkeyList;
        this.context = context;
    }

    public class SmartkeyViewHolder extends RecyclerView.ViewHolder {
        private CardView smartkeyCv;
        private TextView hotelName;
        private TextView roomName;
        private ImageView roomPic;

        public SmartkeyViewHolder(View itemView) {
            super(itemView);
            smartkeyCv = (CardView) itemView.findViewById(R.id.smartkeyCv);
            hotelName = (TextView) itemView.findViewById(R.id.hotelName);
            roomName = (TextView) itemView.findViewById(R.id.roomName);
            roomPic = (ImageView) itemView.findViewById(R.id.roomPic);
        }
    }

    @Override
    public void onBindViewHolder(SmartkeyViewHolder SmartkeyViewHolder, int i) {
        SmartkeyViewHolder.hotelName.setText(SmartkeyList.get(i).getHotelName());
        SmartkeyViewHolder.roomName.setText(SmartkeyList.get(i).getRoomName());
        SmartkeyViewHolder.roomPic.setImageResource(SmartkeyList.get(i).getRoomPic());
    }

    @Override
    public SmartkeyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.smartkey_fragment_cardview, viewGroup, false);
        SmartkeyViewHolder SmartkeyViewHolder = new SmartkeyViewHolder(view);
        return SmartkeyViewHolder;
    }

    @Override
    public int getItemCount() {
        return SmartkeyList.size();
    }
}