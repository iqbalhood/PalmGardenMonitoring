package com.iqbalhood.palmtree.monitoring;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iqbalhood.palmtree.monitoring.fragmentab.RainFallFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.ElaeidobiusFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.GanodermaFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.NumberOfTreesFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.OryctesFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.TurneraSubulataFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.ClaniaFragment;
import com.iqbalhood.palmtree.monitoring.fragmentab.WednesdayFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {


    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link SimpleFragmentPagerAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RainFallFragment();
        } else if (position == 1){
            return new NumberOfTreesFragment();
        } else if (position == 2){
            return new TurneraSubulataFragment();
        } else if (position == 3){
            return new ClaniaFragment();
        } else if (position == 4){
            return new GanodermaFragment();
        } else if (position == 5){
            return new OryctesFragment();
        } else if (position == 6){
            return new ElaeidobiusFragment();
        }else {
            return new WednesdayFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "CH";
        } else if (position == 1) {
            return "\u03A3  Pohon";
        } else if (position == 2) {
            return "U.Api";
        } else if (position == 3) {
            return "U.Kantung";
        } else if   (position == 4) {
            return "Ganoderma";
        }else if (position == 5) {
            return "Oryctes";
        }else if (position == 6) {
            return "Elaeidobius";
        }

        else  {
            return "Lainnya";
        }
    }
}
