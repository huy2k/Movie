package com.example.appp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieDbAdapter movieDbAdapterObj;
    SharedPreferences sharedPreferences ;
    private void jsonParsing(String x)
    {
        MovieParser movieParser =  new MovieParser() {
            @Override
            protected void onPostExecute(ArrayList<MovieData> movieData) {
                movieAdapter = new MovieAdapter(movieData,getActivity());
                recyclerView.setAdapter(movieAdapter);
            }
        };
        movieParser.execute(x);
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView =  rootView.findViewById(R.id.recyclerView);
        movieDbAdapterObj = new MovieDbAdapter(getActivity());

        sharedPreferences = requireActivity().getSharedPreferences("state", Context.MODE_APPEND);
        String type = sharedPreferences.getString("key","popular");
        if(type.equals("fav")&&movieDbAdapterObj.checkMovies()) {
            MovieAdapter dbAdapter = new MovieAdapter(movieDbAdapterObj.getAllFavMovies(), getActivity());
            recyclerView.setAdapter(dbAdapter);

        }
        else
        {jsonParsing(type);}
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        setHasOptionsMenu(true);
        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        sharedPreferences = requireActivity().getSharedPreferences("state",Context.MODE_APPEND );
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(id==R.id.popular)
        {
            editor.putString("key","popular");
            editor.apply();
            jsonParsing("popular");
            return true;
        }

        if(id==R.id.most_rated)
        {
            editor.putString("key","top_rated");
            editor.apply();
            jsonParsing("top_rated");
            return true;
        }
        if(id==R.id.favorite)
        {
            if(!movieDbAdapterObj.checkMovies())
                Message.message(getActivity(),"You have no favorite movies");
            else
            {
                editor.putString("key","fav");
                editor.commit();
                MovieAdapter dbAdapter = new MovieAdapter(movieDbAdapterObj.getAllFavMovies(),getActivity());
                recyclerView.setAdapter(dbAdapter);
                return true;
            }
        }
        if (id == R.id.Chat)
        {
            Intent i = new Intent(this.getActivity(),Messanger.class);
            startActivity(i);
        }
        if ((id == R.id.Profile))
        {
            Intent intent =new Intent(this.getActivity(),RegisterActivity.class);
            intent.putExtra("type","profile");
            startActivity(intent);
        }
        if (id==R.id.Logout)
        {
            Intent intent =new Intent(this.getActivity(),Login_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        if(id == R.id.login){
            Intent intent =new Intent(this.getActivity(),Login_Activity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
