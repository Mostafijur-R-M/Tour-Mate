package com.example.tourmate.mostafijur.tourmate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventList extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> eventList;

    public EventList(Activity context, List<Event> eventList){
        super(context,R.layout.event_row);
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.event_row,null,true);

        TextView eventName = listViewItem.findViewById(R.id.row_event_name);
        TextView eventBudget = listViewItem.findViewById(R.id.row_event_budget);

        Event  event = eventList.get(position);
        eventName.setText(event.getEventName());
        eventBudget.setText((int) event.getBudget());
        return listViewItem;
    }
}
