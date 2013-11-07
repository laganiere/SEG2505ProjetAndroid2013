package com.eecs.seg2505_2013;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Activity context;
    private Map<String, List<String>> sousDomaines;
    private List<String> domaines;
 
    public ExpandableListAdapter(Activity context, List<String> domaines,
            Map<String, List<String>> sousDomaines) {
        this.context = context;
        this.sousDomaines = sousDomaines;
        this.domaines = domaines;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return sousDomaines.get(domaines.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String superDomaine = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
 
        TextView item = (TextView) convertView.findViewById(android.R.id.text1);
        item.setText(superDomaine);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return sousDomaines.get(domaines.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return domaines.get(groupPosition);
    }
 
    public int getGroupCount() {
        return domaines.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(android.R.layout.simple_list_item_1,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(android.R.id.text1);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}