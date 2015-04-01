package example.com.databaseapplication;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Alexandru on 21-Mar-15.
 */
public class ListViewAdapter extends BaseAdapter {
    /**
     * The inflater.
     */
    protected LayoutInflater inflater;

    private Context context;

    /**
     * The items list. Here you can set any type, I used String because I was interested only in passing a string, but you get the idea
     */
    protected List<Product> items;

    public ListViewAdapter(Context context, List<Product> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //if it is a new item
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_product_layout, parent, false);

            holder = new ViewHolder();

            holder.productImage = (ImageView) convertView.findViewById(R.id.list_view_product_image);
            holder.productName = (TextView) convertView.findViewById(R.id.list_view_product_name_text_view);
            holder.productQuantity = (TextView) convertView.findViewById(R.id.list_view_product_quantity_text_view);
            holder.productID = (TextView) convertView.findViewById(R.id.list_view_product_id_text_view);

            convertView.setTag(holder);
        } else {
            //here we recycle the previous ViewHolder, by using an older one
            holder = (ViewHolder) convertView.getTag();
        }

        holder.productImage.setImageBitmap(items.get(position).getProductImage());
        holder.productName.setText(items.get(position).getProductName());
        holder.productQuantity.setText(items.get(position).getProductQuantity() + "");
        holder.productID.setText(items.get(position).getProductID() + "");

        return convertView;
    }

    //in ViewHolder we will keep the layout for the listview items, so if we need to add an imageview to our items, we will add a new ImageViwe variable in the ViewHolder class
    private class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productQuantity;
        TextView productID;
    }
}