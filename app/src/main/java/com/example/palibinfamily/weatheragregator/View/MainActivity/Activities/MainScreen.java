package com.example.palibinfamily.weatheragregator.View.MainActivity.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.palibinfamily.weatheragregator.Model.WeatherSnapshot;
import com.example.palibinfamily.weatheragregator.Presenter.MainActivityPresenter;
import com.example.palibinfamily.weatheragregator.R;
import com.example.palibinfamily.weatheragregator.TmpClassesForTesting.Dummies;
import com.example.palibinfamily.weatheragregator.View.MainActivity.Activities.CustomViews.MainView;

import com.example.palibinfamily.weatheragregator.View.MainActivity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {..@link MainScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreen extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private final String TAG = "MainScreen";
    SwipeRefreshLayout mSwipeRefreshLayout;
//    private OnFragmentInteractionListener mListener;

    public MainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(int sectionNumber,WeatherSnapshot snapshot) {
        Fragment fragment = new MainScreen();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        args.putSerializable("data", Dummies.generateTestData().get(sectionNumber-1));
        args.putSerializable("data", snapshot);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        Bundle bundle=getArguments();

        //here is your list array
        WeatherSnapshot snap = (WeatherSnapshot) bundle.getSerializable("data");

        MainView mainView = rootView.findViewById(R.id.MainView);
        Log.d("PlaceholderFragment","snap: " + snap);
        mainView.setSnapShot(snap);
        mainView.setDayNumber(bundle.getInt(ARG_SECTION_NUMBER)-1);
        mainView.setPresenter(MainActivity.getPresenter());

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return rootView;
    }

    @Override
    public void onRefresh() {
// говорим о том, что собираемся начать
//        Toast.makeText(getContext(), "refresh", Toast.LENGTH_SHORT).show();
//        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        try {
            MainActivity.getPresenter().downloadWeatherValues(7);
        }catch (Exception e){

        }
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить
//                Toast.makeText(getContext(), "refresh finished", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
