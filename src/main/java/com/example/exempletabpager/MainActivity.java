package com.example.exempletabpager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.exempletabpager.fragment.FirstFragment;
import com.example.exempletabpager.fragment.MainFragment;
import com.example.exempletabpager.fragment.SecondFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    FragmentManager fragmentManager;
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On Instancie nos fragments
        mainFragment = new MainFragment();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();

        //On ajoute nos fragments dans une liste de fragments
        fragments = new ArrayList<Fragment>();
        fragments.add(mainFragment);
        fragments.add(firstFragment);
        fragments.add(secondFragment);

        //On gère notre tablayout
        TabLayout tabLayout = findViewById(R.id.tablayout);

        //On ajoute les tabs dynamiquement
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Lundi");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Mardi");
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Mercredi");
        tabLayout.addTab(thirdTab);

        fragmentManager = getFragmentManager();

        //Fragment affiché par défaut au démarrage de l'application
        ReplaceFragment(mainFragment);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //On récupère la position du tabitem
                int position = tab.getPosition();
                //On rechercher dans l'array list le fragment correspondant par rapport à son index
                Fragment fragment = fragments.get(position);
                ReplaceFragment(fragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        }

    private void ReplaceFragment(Fragment fragment)
    {
        //On démarre une transaction pour gérer les différents fragments
        fragmentManager.beginTransaction()
                .replace(R.id.frmMain, fragment)
                .commit();
    }
}

