package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.DTO.Category;
import com.example.myapplication.ProductsActivity;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter  extends BaseAdapter {
List <Category> Categories ;
Context context;
LayoutInflater inflater ;
    public CategoriesAdapter(Context con, ArrayList<Category> categories) {
        Categories = categories;
        context=con;
        inflater = LayoutInflater.from(con);
    }

    @Override
    public int getCount() {
        return Categories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
       View view = inflater.inflate(R.layout.grid_item,null);
        ImageView cate_img = view.findViewById(R.id.cat_image);
        TextView cat_name = view.findViewById(R.id.cat_name);
        Picasso.get().load(Categories.get(position).getCategory_img()).placeholder(R.drawable.ic_launcher_background).into(cate_img);
        cat_name.setText(Categories.get(position).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productsIntent = new Intent(context, ProductsActivity.class);
                productsIntent.putExtra("category", Categories.get(position).getId());
                context.startActivity(productsIntent);
            }
        });
        return view;
    }
}
