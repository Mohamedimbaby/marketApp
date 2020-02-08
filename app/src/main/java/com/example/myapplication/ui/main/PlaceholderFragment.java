package com.example.myapplication.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapters.CategoriesAdapter;
import com.example.myapplication.Adapters.ProductsAdapter;
import com.example.myapplication.Adapters.slideAdapter;
import com.example.myapplication.DTO.Category;
import com.example.myapplication.DTO.Product;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    int category;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        gridView=root.findViewById(R.id.product_grid_view);

        category = getActivity().getIntent().getExtras().getInt("category");

        getProducts();


        return root;
    }
    void getProducts() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request =
                new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Product> productsList = new ArrayList<>();
                        try {
                            JSONObject categoryObject = response.getJSONObject(category - 1);
                            JSONArray products = categoryObject.getJSONArray("products");
                            for (int i = 0; i < products.length(); i++) {
                                Product p = new Product();
                                JSONObject productObject = products.getJSONObject(i);
                                p.setId(productObject.getInt("id"));
                                p.setName(productObject.getString("name"));
                                p.setPrice(productObject.getString("price"));
                                p.setProduct_img(productObject.getString("product_img"));
                                productsList.add(p);
                                                  }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ProductsAdapter adapter = new ProductsAdapter(getContext(),productsList );
                        System.out.println(productsList.size());
                        gridView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(request);

    }
    String url="https://5bcce576cf2e850013874767.mockapi.io/task/categories";
    GridView gridView ;

}