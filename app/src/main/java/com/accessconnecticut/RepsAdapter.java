package com.accessconnecticut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KTirumalsetty on 12/22/2016.
 */

public class RepsAdapter extends ArrayAdapter<Legislators>{

    Context context;
    LayoutInflater inflater;
    List<Legislators> legislatorsList;

    public RepsAdapter(Context context,
                                List<Legislators> legislatorsList) {
        super(context,0,legislatorsList);
        this.legislatorsList = legislatorsList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        NetworkImageViewRounded networkImageView;
        TextView name;

    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        Legislators legislator = legislatorsList.get(position);
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_reps_item, null);
            // Locate the TextViews in listview_item.xml
            holder.networkImageView = (NetworkImageViewRounded) view.findViewById(R.id.network_image_view);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(legislator.getFull_name());
        holder.networkImageView.setDefaultImageResId(R.drawable.connecticut_seal_120x120);
       holder.networkImageView.setImageUrl(legislator.getPhoto_url(),NetworkManager.getInstance().getImageLoader());

        return view;
    }

    @Override
    public void remove(Legislators object) {

        legislatorsList.remove(object);
        notifyDataSetChanged();
    }

    public List<Legislators> getCodesList() {
        return legislatorsList;
    }

}
