package com.deepanshibansal.increasingvocab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
RecyclerView.LayoutManager layoutManager;
RecyclerView.Adapter mAdapter;
EditText word_editText, meaning_editText;
    List<String> list=new ArrayList<>();
    List<String> meaning=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("Ecstatic");
        meaning.add("Excited");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                final View promptsView = li.inflate(R.layout.vocab_add, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(promptsView);

                word_editText = (EditText) promptsView.findViewById(R.id.word_editText);
                meaning_editText = (EditText) promptsView.findViewById(R.id.meaning_editText);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Add to Vocabulary", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                list.add(word_editText.getText().toString());
                                meaning.add(meaning_editText.getText().toString());
                                mAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String wordstring = TextUtils.join(";", list);
                                editor.putString("word", wordstring);
                                String meaningstring = TextUtils.join(";", meaning);
                                editor.putString("meaning", meaningstring);
                                editor.commit();

                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        SharedPreferences mSharedPreference1 = getPreferences(MODE_PRIVATE);
        final String countriesString = mSharedPreference1.getString("word", "");
        List<String> countries = new ArrayList<>();
        if (!countriesString.isEmpty()){
            countries = new ArrayList<>(Arrays.asList(countriesString.split(";")));
        }
        final String citiesString = mSharedPreference1.getString("meaning", "");
        List<String> cities = new ArrayList<>();
        if (!citiesString.isEmpty()){
            cities = new ArrayList<>(Arrays.asList(citiesString.split(";")));
        }
list=countries;
meaning=cities;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new words(list,meaning);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
