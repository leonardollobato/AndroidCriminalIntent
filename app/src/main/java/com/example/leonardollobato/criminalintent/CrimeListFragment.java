package com.example.leonardollobato.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by leonardollobato on 5/30/16.
 */
public class CrimeListFragment extends Fragment {

    private static final String TAG = "**CrimeListFragment**";

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        Log.d(TAG, " CrimeListFragment *updateUI*");
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);

            Log.d(TAG, " CrimerHolder *Constructor*");
        }

        public void bindCrime(Crime crime){
            mCrime = crime;

            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }


        @Override
        public void onClick(View v) {
            StringBuilder message = new StringBuilder(mCrime.getTitle() + " was clicked -> ");

            switch (v.getId()){
                case R.id.list_item_crime_title_text_view:
                    message.append(" TITLE");
                    break;
                case R.id.list_item_crime_solved_check_box:
                    message.append(" CHECKBOX");
                    break;
                case R.id.list_item_crime_date_text_view:
                    message.append(" DATE");
                    break;
                default:
                    message.append(" NONE");
                    break;
            }
            Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
            Log.d(TAG, " CrimeAdapter *Constructor*");
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, " CrimeAdapter *onCreateViewHolder*");
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);

        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Log.d(TAG, " CrimeAdapter *onBindViewHolder* " + position);
            Crime crime = mCrimes.get(position);

            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, " CrimeAdapter *getItemCount*");
            return mCrimes.size();
        }
    }
}
