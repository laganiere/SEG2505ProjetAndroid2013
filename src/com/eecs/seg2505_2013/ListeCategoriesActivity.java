package com.eecs.seg2505_2013;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.swarmconnect.SwarmActivity;

public class ListeCategoriesActivity extends SwarmActivity {
	
	List<String> groupList;
    Map<String, List<String>> sousDomaines; // sous-domaine
    ExpandableListView liste_categories;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_categories);
		
		groupList= ((MyApplication)getApplicationContext()).getSuperDomaines();
		sousDomaines= ((MyApplication)getApplicationContext()).getSousDomaines();

		liste_categories = (ExpandableListView)findViewById(R.id.expandableListViewCategorie);
		
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, sousDomaines);
		liste_categories.setAdapter(expListAdapter);
 
        setGroupIndicatorToRight();
 
		liste_categories.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();
 
                return true;
            }
        });
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste_categories, menu);
		return true;
	}
    
    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
 
        liste_categories.setIndicatorBounds(width - getDipsFromPixel(100), width
                - getDipsFromPixel(5));
    }
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
}
