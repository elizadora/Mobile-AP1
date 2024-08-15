package com.example.todolist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemArrayAdapter extends  RecyclerView.Adapter<ItemArrayAdapter.ViewHolder>{
    private int listItemLayout;
    private ArrayList<Item> itemList;


    public ItemArrayAdapter(int layoutId, ArrayList<Item> itemList){
        this.listItemLayout = layoutId;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView itemTitulo = holder.itemTitulo;
        TextView itemDescricao = holder.itemDescricao;

        itemTitulo.setText(itemList.get(position).getTitulo());
        itemDescricao.setText(itemList.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemTitulo;
        public TextView itemDescricao;
        public CheckBox btnConcluir;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemTitulo = (TextView) itemView.findViewById(R.id.item_titulo);
            itemDescricao = (TextView) itemView.findViewById(R.id.item_descricao);
            btnConcluir = (CheckBox) itemView.findViewById(R.id.btn_concluir);


            btnConcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btnConcluir.isChecked()){
                        itemTitulo.setPaintFlags(itemTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }else{
                        itemTitulo.setPaintFlags(itemTitulo.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
        }


        @Override
        public void onClick(View v) {
            String name = itemTitulo.getText().toString();
            Toast.makeText(v.getContext(), "Você selecionou: " + name, Toast.LENGTH_SHORT).show();
        }

    }
}