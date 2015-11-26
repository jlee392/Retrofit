package com.example.oose.routemaker.CreateTrip;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by user01 on 2015-11-24.
 */
public class SelectSitesSitesFragment extends Fragment {


    public static SelectSitesSitesFragment newInstance(int index) {
        SelectSitesSitesFragment s = new SelectSitesSitesFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);

        s.setArguments(args);

        return s;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HorizontalScrollView scroller = new HorizontalScrollView(getActivity());
        TextView text = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        text.setPadding(padding, padding, padding, padding);

        scroller.addView(text);

        text.setText(SelectSitesInfo.DAYS[getShownIndex()] + " " + SelectSitesInfo.CATEGORIES[getShownIndex()]);

        return scroller;
    }
}
