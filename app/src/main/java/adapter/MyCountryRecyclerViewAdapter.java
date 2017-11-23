package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m7mdmimo.g2mdx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> countries;

    public MyCountryRecyclerViewAdapter(ArrayList<String> items) {
        countries = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String name = countries.get(position);
        holder.textView_country_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void filterList(ArrayList<String> filterdNames) {
        this.countries = filterdNames;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_country_name)
        TextView textView_country_name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }


    }
}
