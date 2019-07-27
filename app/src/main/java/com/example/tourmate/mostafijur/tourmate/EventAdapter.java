package com.example.tourmate.mostafijur.tourmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{
    private Context context;
    private List<Event> eventList;
    private ItemActionListener listener;
    private listItemActionClickListener listItemActionClickListener;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        listener = (ItemActionListener) context;
        listItemActionClickListener = (EventAdapter.listItemActionClickListener) context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.event_row, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        holder.eventNameTV.setText(eventList.get(position).getEventName());
        holder.budgetTV.setText(String.valueOf(eventList.get(position).getBudget()));
        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.person_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final String id = eventList.get(position).getEventId();

                        switch (item.getItemId()){
                            /*case R.id.menu_edit:
                                listener.onItemEdit(id);
                                break;*/
                            case R.id.menu_delete:
                                listener.onItemDelete(id);
                                break;
                        }
                        return false;
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String eventId = eventList.get(position).getEventId();
               String eventName = eventList.get(position).getEventName();
               String createDate = eventList.get(position).getCreateDate();
               String startDate = eventList.get(position).getStartDate();
               String budget = Double.toString(eventList.get(position).getBudget());



               listItemActionClickListener.onListItemActionClickListener(eventId, eventName, createDate, startDate, budget);

                Toast.makeText(context,eventList.get(position).getEventName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameTV, budgetTV, menuTV;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventNameTV = itemView.findViewById(R.id.row_event_name);
            budgetTV = itemView.findViewById(R.id.row_event_budget);
            menuTV = itemView.findViewById(R.id.row_event_menu);
        }
    }

    public void updateList(List<Event> eventList){
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    interface ItemActionListener {
        void onItemDelete(String rowId);
        void onItemEdit(String rowId);

    }
    interface listItemActionClickListener{
        void onListItemActionClickListener(String eventId, String  eventName, String createDate, String startDate, String budget);
    }
}
