package com.example.py.wirelesslocator;


import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class TabSecond extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_second,
                container, false);

        MapView imageView = view.findViewById(R.id.mapView1);
        imageView.setPin(new PointF(460f, 320f));

        setHasOptionsMenu(true);

        return view;
    }

 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_share:
                //do something
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
*/
    public interface OnFragmentInteractionListener {
    }
}
