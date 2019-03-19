package edu.cascadia.mobas.gybitg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;

import edu.cascadia.mobas.gybitg.viewmodel.StatFragmentViewModel;
import edu.cascadia.mobas.gybitg.databinding.FragmentStatsBinding;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // Create reference of StatFragmentViewModel
    private StatFragmentViewModel mFragmentViewModel;

    public StatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatFragment newInstance(String param1, String param2) {
        StatFragment fragment = new StatFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Create a new binding using DataBindingUtil
        FragmentStatsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_stats, container, false);

        // Initialize the view
        View view = binding.getRoot();

        // Initialize the new StatFragmentViewModel
        mFragmentViewModel = new StatFragmentViewModel(this.getActivity().getApplication());

        // Set binding ViewModel and LifecycleOwner
        binding.setViewModel(mFragmentViewModel);
        binding.setLifecycleOwner(this);

        return  view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // setup reference and initialize the Add Game button
        final Button btn_add_game = view.findViewById(R.id.add_game);

        // reference to the view stat history button
        final Button btn_view_history = view.findViewById(R.id.btn_view_history);
        final StatHistoryFragment statsHistoryFragment = new StatHistoryFragment();


        // set up OnClick listener for Add Game button
        btn_add_game.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // set up reference to new Intent
                        final Intent editStat = new Intent(getActivity(), EditStatActivity.class);
                        startActivity(editStat);
                    }
                });

        // setup OnClick listener for View History button
        btn_view_history.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assert getFragmentManager() != null;
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.profile_fragment_container, statsHistoryFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );

        // Load the stats into the view
        loadStats();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

            loadStats();
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        } */
    }

    // Load the Athlete career average stats
    private void loadStats() {
        mFragmentViewModel.loadStats();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
