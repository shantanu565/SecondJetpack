package com.shantanu.example.secondjetpack;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shantanu.example.secondjetpack.databinding.RowlayoutBinding;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<User> userModelList;
    private RowlayoutBinding rowItemLayoutBinding;

    public void setUserModelList(List<User> userModelList) {
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        rowItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.rowlayout, viewGroup, false);
        return new ViewHolder(rowItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myViewHolder, int i) {
        User userModel = userModelList.get(i);
        rowItemLayoutBinding.tvemail.setText(userModel.getUserEmail());
        rowItemLayoutBinding.tvname.setText(userModel.getUserName());
        rowItemLayoutBinding.tvphone.setText(userModel.getUserPhone());


    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(RowlayoutBinding rowItemLayoutBinding) {
            super(rowItemLayoutBinding.getRoot());
        }
    }
}