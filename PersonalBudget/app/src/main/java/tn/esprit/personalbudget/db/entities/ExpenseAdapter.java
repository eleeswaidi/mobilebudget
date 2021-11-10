package tn.esprit.personalbudget.db.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.personalbudget.R;

public class ExpenseAdapter  extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    List<Expense> expenses=new ArrayList<>();
    Context context;

    public ExpenseAdapter(List<Expense> expenses,Context context){
       this. expenses=expenses;
        this.context=context;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.item_expense,parent,false);
        return new  ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense e=expenses.get(position);
        holder.nameexp.setText(e.getName());
        holder.cost.setText("-"+ Integer.toString( e.getCost())+"DT");
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class  ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView nameexp;
        TextView cost;
        public ExpenseViewHolder(View itemView){
            super(itemView);
            nameexp=itemView.findViewById(R.id._nameexp);
            cost=itemView.findViewById(R.id._cost);
        }
    }
}
