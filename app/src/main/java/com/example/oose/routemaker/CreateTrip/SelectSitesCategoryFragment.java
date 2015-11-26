package com.example.oose.routemaker.CreateTrip;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oose.routemaker.R;

public class SelectSitesCategoryFragment extends ListFragment {

    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> connectArrayToListView = new
                ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                SelectSitesInfo.CATEGORIES);

        setListAdapter(connectArrayToListView);
        View SitesFrame = getActivity().findViewById(R.id.select_site_sites_fragment);

//        mDuelPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        showDetails(mCurCheckPosition);

//        if(mDuelPane) {
//            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//            showDetails(mCurCheckPosition);
//        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetails(position);
    }

    void showDetails(int index) {
        mCurCheckPosition = index;

        getListView().setItemChecked(index, true);

        SelectSitesSitesFragment sites = (SelectSitesSitesFragment) getFragmentManager().findFragmentById(R.id.select_site_sites_fragment);

        if(sites == null || sites.getShownIndex() != index) {
            sites = SelectSitesSitesFragment.newInstance(index);

            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.replace(R.id.select_site_sites_fragment, sites);

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        }
        /* else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), SelectSitesSitesActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
*/
       /* if(mDuelPane){
            getListView().setItemChecked(index, true);

            DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);

            if(details == null || details.getShownIndex() != index){
                details = DetailsFragment.newInstance(index);

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.details, details);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else {

                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailsActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        }*/
    }
}
