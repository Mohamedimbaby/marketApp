package com.example.myapplication.ui.home;

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
import com.example.myapplication.Adapters.slideAdapter;
import com.example.myapplication.DTO.Category;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = root.findViewById(R.id.gridView);
        viewPager = root.findViewById(R.id.cat_view_pager);
        dotsLayout = root.findViewById(R.id.cat_splash);
        createDots(0);
        slideAdapter adapter = new slideAdapter(getContext(), false);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getCategories();



        return root;
    }

    void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request =
                new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Category> Categories = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Category category = new Category();
                                JSONObject jsonObject = response.getJSONObject(i);
                                category.setName(jsonObject.getString("name"));
                                category.setId(jsonObject.getInt("id"));
                                category.setCategory_img(jsonObject.getString("category_img"));
                                Categories.add(category);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), Categories);
                        gridView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(request);

    }
    void createDots(int currentPosition) {
        if (dotsLayout != null)
            dotsLayout.removeAllViews();
        dots = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            dots[i] = new ImageView(getContext());
            if (i <= currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.white_selected));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.not_selected_dots));

            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            dotsLayout.addView(dots[i], params);
        }


    }

    String url="https://5bcce576cf2e850013874767.mockapi.io/task/categories";
    GridView gridView ;
    ViewPager viewPager ;
    LinearLayout dotsLayout;
    ImageView[] dots ;

}