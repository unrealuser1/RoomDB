package com.unreal.roomdb.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unreal.roomdb.R;
import com.unreal.roomdb.database.MainData;
import com.unreal.roomdb.database.RoomDB;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;
    public CustomAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        MainData data = dataList.get(position);
        database = RoomDB.getInstance(context);

        holder.Name.setText(data.getText());
//        holder.Edit.setOnClickListener(view -> {
//            MainData d = dataList.get(holder.getBindingAdapterPosition());
//            int sID = d.getID();
//            String sText = d.getText();
//
//            Dialog dialog = new Dialog(context);
//            dialog.setContentView(R.layout.dialog_update);
//            dialog.show();
//
//            EditText edittext = dialog.findViewById(R.id.et_dialog);
//            Button button = dialog.findViewById(R.id.btn_dialog_update);
//
//            edittext.setText(sText);
//            button.setOnClickListener(view1 -> {
//                dialog.dismiss();
//                String uText = edittext.getText().toString().trim();
//                database.mainDao().update(sID, uText);
//                dataList.clear();
//                dataList.addAll(database.mainDao().getAll());
//                notifyDataSetChanged();
//            });
//        });
//        holder.Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainData d = dataList.get(holder.getBindingAdapterPosition());
//                database.mainDao().delete(d);
//                int position = holder.getBindingAdapterPosition();
//                dataList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, dataList.size());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Edit, Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.row_name);
            Edit = itemView.findViewById(R.id.edt_row);
            Delete = itemView.findViewById(R.id.dlt_row);
        }
    }
}
