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
import com.example.myapplication.DTO.Product;
import com.example.myapplication.ProductsActivity;
import com.example.myapplication.R;
import com.example.myapplication.products_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends BaseAdapter {
List <Product> Products ;
Context context;
LayoutInflater inflater ;
    public ProductsAdapter(Context con, ArrayList<Product> Products_list) {
        Products = Products_list;
        context=con;
        inflater = LayoutInflater.from(con);
    }

    @Override
    public int getCount() {
        return Products.size();
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
       View view = inflater.inflate(R.layout.products_grid_item,null);
        ImageView product_image = view.findViewById(R.id.product_image);
        TextView Product_name = view.findViewById(R.id.product_name);
        TextView product_price = view.findViewById(R.id.product_price);
       Picasso.get().load(Products.get(position).getProduct_img()).placeholder(R.drawable.ic_launcher_background).into(product_image);
        Product_name.setText(Products.get(position).getName());
        product_price.setText(Products.get(position).getPrice());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productsIntent = new Intent(context, products_details.class);
                productsIntent.putExtra("title", Products.get(position).getName());
                context.startActivity(productsIntent);
            }
        });
        return view;
    }
}
