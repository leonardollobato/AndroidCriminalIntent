package com.example.leonardollobato.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by leonardollobato on 5/30/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
