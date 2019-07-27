package com.example.tourmate.mostafijur.tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourmate.mostafijur.tourmate.forecastweather.List;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>{
    private Context context;
    private java.util.List<Expense> expenseList;
    private ExpenseAdapter.ItemActionListener listener;
    private ExpenseAdapter.listItemActionClickListener listItemActionClickListener;
    public ExpenseAdapter(Context context, java.util.List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
        listener = (ExpenseAdapter.ItemActionListener) context;
        listItemActionClickListener = (ExpenseAdapter.listItemActionClickListener) context;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.event_row, parent, false);
        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, final int position) {

        holder.expenseNameTV.setText(expenseList.get(position).getEexpenseName());
        holder.amountTV.setText(String.valueOf(expenseList.get(position).getExpenseAmount()));
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
                        final String id = expenseList.get(position).getEventId();

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

                String eventId = expenseList.get(position).getEventId();
                String expenseName = expenseList.get(position).getEexpenseName();
                String amount = Double.toString(expenseList.get(position).getExpenseAmount());



                listItemActionClickListener.onListItemActionClickListener(eventId, expenseName, amount);

                Toast.makeText(context,expenseList.get(position).getEexpenseName(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseNameTV, amountTV, menuTV;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            expenseNameTV = itemView.findViewById(R.id.row_event_name);
            amountTV = itemView.findViewById(R.id.row_event_budget);
            menuTV = itemView.findViewById(R.id.row_event_menu);
        }
    }

    public void updateList(java.util.List<Expense> expenseList){
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    interface ItemActionListener {
        void onItemDelete(String rowId);
        void onItemEdit(String rowId);

    }
    interface listItemActionClickListener{
        void onListItemActionClickListener(String eventId, String  expenseName, String amount);
    }

}
