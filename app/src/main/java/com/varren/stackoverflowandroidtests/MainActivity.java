package com.varren.stackoverflowandroidtests;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentA fragmentA = new FragmentA();
            FragmentB fragmentB = new FragmentB();

            fragmentA.registerListener(fragmentB.getActionsListener());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerA, fragmentA)
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerB, fragmentB)
                    .commit();

        }
    }


    public static class FragmentA extends Fragment {

        public interface OnButtonClickActionListener{
            void onButton1Click();
            void onButton2Click();
        }
        private OnButtonClickActionListener listener;

        public void registerListener(OnButtonClickActionListener listener){
            this.listener = listener;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.fragment_a_layout, container, false);
            Button button1 = (Button) rootView.findViewById(R.id.white_color_button);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!= null){
                        listener.onButton1Click();
                    }
                }
            });

            Button button2 = (Button) rootView.findViewById(R.id.red_color_button);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onButton2Click();
                    }
                }
            });

            return rootView;
        }
    }

    public static class FragmentB extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.fragment_b_layout, container, false);
            return rootView;

        }

        public FragmentA.OnButtonClickActionListener getActionsListener() {
            return new FragmentA.OnButtonClickActionListener() {
                @Override
                public void onButton1Click() {
                    Log.e("Button1", "Click");
                    getView().setBackgroundColor(Color.RED);
                }

                @Override
                public void onButton2Click() {
                    Log.e("Button2", "Click");
                    getView().setBackgroundColor(Color.GREEN);
                }
            };
        }
    }
}
