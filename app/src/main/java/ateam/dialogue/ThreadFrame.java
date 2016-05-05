package ateam.dialogue;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThreadFrame extends Fragment {

    public ThreadFrame() {
    }

    private List<Thread> threadList = new ArrayList<>();
    private RecyclerView threadRV;
    private ThreadAdapter threadAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        super.onCreate(savedInstanceState);

        System.out.println("rootView set");

        threadRV = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        System.out.println("threadRV set");

        threadAdapter = new ThreadAdapter(threadList);
        System.out.println("ADAPTER SET");
        threadRV.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        threadRV.setLayoutManager(mLayoutManager);
        System.out.println("LAYOUT MANAGER SET");
        //threadRV.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        threadRV.setItemAnimator(new DefaultItemAnimator());
        threadRV.setAdapter(threadAdapter);

        threadRV.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), threadRV, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Thread movie = threadList.get(position);
                Toast.makeText(getActivity().getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareThreadData();
        return rootView;
    }

    private void prepareThreadData() {

        Thread movie = new Thread("Mad Max: Fury Road", "Action & Adventure", "2015");
        threadList.add(movie);

        movie = new Thread("Inside Out", "Animation, Kids & Family", "2015");
        threadList.add(movie);

        movie = new Thread("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        threadList.add(movie);

        movie = new Thread("Shaun the Sheep", "Animation", "2015");
        threadList.add(movie);

        movie = new Thread("The Martian", "Science Fiction & Fantasy", "2015");
        threadList.add(movie);

        movie = new Thread("Mission: Impossible Rogue Nation", "Action", "2015");
        threadList.add(movie);

        movie = new Thread("Up", "Animation", "2009");
        threadList.add(movie);

        movie = new Thread("Star Trek", "Science Fiction", "2009");
        threadList.add(movie);

        movie = new Thread("The LEGO Movie", "Animation", "2014");
        threadList.add(movie);

        movie = new Thread("Iron Man", "Action & Adventure", "2008");
        threadList.add(movie);

        movie = new Thread("Aliens", "Science Fiction", "1986");
        threadList.add(movie);

        movie = new Thread("Chicken Run", "Animation", "2000");
        threadList.add(movie);

        movie = new Thread("Back to the Future", "Science Fiction", "1985");
        threadList.add(movie);

        movie = new Thread("Raiders of the Lost Ark", "Action & Adventure", "1981");
        threadList.add(movie);

        movie = new Thread("Goldfinger", "Action & Adventure", "1965");
        threadList.add(movie);

        movie = new Thread("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        threadList.add(movie);

        threadAdapter.notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ThreadFrame.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ThreadFrame.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}

















