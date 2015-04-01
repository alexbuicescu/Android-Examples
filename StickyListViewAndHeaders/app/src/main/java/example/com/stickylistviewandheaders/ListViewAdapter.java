package example.com.stickylistviewandheaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Alexandru on 16-Mar-15.
 */
public class ListViewAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

    Context context;
    ArrayList<String> currentItems;
    ArrayList<String> currentHeaders;
    private HashMap<String, Integer> indexer;
    protected LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<String> currentItems) {

        this.context = context;
        this.currentItems = currentItems;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initIndexer();
    }

    private void initIndexer() {
        indexer = new HashMap<>();

        Collections.sort(currentItems);

        for (int i = 0; i < currentItems.size(); i++) {
            if(indexer.get(currentItems.get(i).toUpperCase().charAt(0) + "") == null)
            {
                indexer.put(currentItems.get(i).toUpperCase().charAt(0) + "", i);
            }
        }

        Set<String> keys = indexer.keySet();
        currentHeaders = new ArrayList<>(keys);
        Collections.sort(currentHeaders);
    }

    public void setCurrentItems(ArrayList<String> currentItems) {
        this.currentItems = currentItems;
    }

    @Override
    public View getHeaderView(int i, View convertView, ViewGroup parent) {
        DividerViewHolder holder;

        if (convertView == null) {
            holder = new DividerViewHolder();
            convertView = inflater.inflate(R.layout.list_view_header_layout, parent, false);
            holder.groupName = (TextView) convertView.findViewById(R.id.list_view_header);
            convertView.setTag(holder);
        } else {
            holder = (DividerViewHolder) convertView.getTag();
        }

        holder.groupName.setText("Header: " + currentItems.get(i).toUpperCase().charAt(0));

        return convertView;
    }

    @Override
    public long getHeaderId(int i) {
        return currentItems.get(i).toUpperCase().charAt(0);
    }

    @Override
    public int getPositionForSection(int section) {
        try {
            return indexer.get(currentHeaders.get(section));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < currentHeaders.size(); i++) {
            if (position < indexer.get(currentHeaders.get(i))) {
                return i - 1;
            }
        }
        return currentHeaders.size() - 1;
    }

    @Override
    public Object[] getSections() {
        return currentHeaders.toArray();
    }

    @Override
    public int getCount() {
        return currentItems.size();
    }

    @Override
    public Object getItem(int position) {
        return currentItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //if it is a new item
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item_layout, parent, false);

            holder = new ViewHolder();

            holder.titleTextView = (TextView) convertView.findViewById(R.id.list_view_item_title);
            holder.subTitleTextView = (TextView) convertView.findViewById(R.id.list_view_item_subtitle);

            convertView.setTag(holder);
        } else {
            //here we recycle the previous ViewHolder, by using an older one
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTextView.setText("Title: " + currentItems.get(position));
        holder.subTitleTextView.setText("Subtitle: " + currentItems.get(position));

        return convertView;
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView subTitleTextView;
    }

    public static class DividerViewHolder {
        TextView groupName;
    }
}
