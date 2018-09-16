package com.msnegi.listxmlvolley;

import java.io.InputStream;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter
{
	Context context;
	List<Product> products;
	ImageLoader imageLoader;
	
	public CustomBaseAdapter(Context context, List<Product> items)
	{
		this.context = context;
		this.products = items;
	}

	static class ViewHolder
	{
		NetworkImageView thumbNail;
		TextView txtTitle;
		TextView txtDesc;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.list_item, null);			
			
			holder = new ViewHolder();
			holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
			holder.thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);	
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		Product product = (Product) getItem(position);

		holder.txtDesc.setText(product.getdescription());
		holder.txtTitle.setText(product.gettitle());
		
		try
		{
			Log.e("==>",product.getimage());

			if (imageLoader == null) imageLoader = AppController.getInstance().getImageLoader();
			holder.thumbNail.setImageUrl(product.getimage(), imageLoader);
		}
		catch(Exception e)
		{}

		return convertView;
	}

	@Override
	public int getCount()
	{
		return products.size();
	}

	@Override
	public Object getItem(int position)
	{
		return products.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return products.indexOf(getItem(position));
	}
}