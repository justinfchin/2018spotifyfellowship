// for RecyclerView
package com.justinfchin.calendarapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.justinfchin.calendarapp.retrofit.EventItem;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<EventItem> dataSet;

    public RVAdapter (List<EventItem> dataSet){
        this.dataSet = dataSet;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvTitle, rvStartTime, rvEndTime;
        public ViewHolder(View v){
            super(v);
            rvTitle = v.findViewById(R.id.rvTitle);
            rvStartTime = v.findViewById(R.id.rvStartTime);
            rvEndTime = v.findViewById(R.id.rvEndTime);

        }
    }



    /** Creates New Views
     *
     * @param parent View
     * @param viewType
     * @return ViewHolder
     */
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list,parent,false);
        return new ViewHolder(v);
    }

    /** Replaces Contents of a View
     *
     * @param holder View
     * @param position Index
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){

            holder.rvTitle.setText(dataSet.get(position).getEventTitle());
            holder.rvStartTime.setText(dataSet.get(position).getStartTime());
            holder.rvEndTime.setText(dataSet.get(position).getEndTime());
            holder.rvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                    alertDialog.setTitle("Details");
                    alertDialog.setMessage(dataSet.get(holder.getAdapterPosition()).getEventDescription());
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            });

    }

    /** Size of dataSet
     *
     * @return length of dataSet
     */
    @Override
    public int getItemCount(){
        return dataSet.size();
    }
}

