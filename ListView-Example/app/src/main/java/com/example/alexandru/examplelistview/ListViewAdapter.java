package com.example.alexandru.examplelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alexandru on 02-Oct-14.
 */
public class ListViewAdapter extends BaseAdapter {
	/**
	 * The inflater.
	 */
	protected LayoutInflater inflater;

	/**
	 * The items list.
	 */
	protected List<String> items;

	/**
	 * The context.
	 */
	private Context context;

	/**
	 * The Class ViewHolder.
	 */
	private static class ViewHolder {
		TextView theTitleTextView;
		TextView theSubtitleTextView;
	}

	public ListViewAdapter(Context context, List<String> items) {
		this.context = context;
		this.items = items;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_custom_item, parent, false);

			holder = new ViewHolder();

			holder.theTitleTextView = (TextView) convertView.findViewById(R.id.listview_adapter_title_textview);
			holder.theSubtitleTextView = (TextView) convertView.findViewById(R.id.listview_adapter_subtitle_textview);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.theTitleTextView.setText(items.get(position));
		holder.theSubtitleTextView.setText("Item #" + position);

		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int i) {
		return items.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

}
