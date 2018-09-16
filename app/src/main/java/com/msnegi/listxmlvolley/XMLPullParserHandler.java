package com.msnegi.listxmlvolley;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class XMLPullParserHandler
{
	List<Product> products;
	private Product product;
	private String text;

	public XMLPullParserHandler()
	{
		products = new ArrayList<Product>();
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public List<Product> parse(String is)
	{
		XmlPullParserFactory factory = null;
		XmlPullParser parser = null;
		try
		{
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			parser = factory.newPullParser();

			parser.setInput( new StringReader(is));

			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT)
			{
				String tagname = parser.getName();
				switch (eventType)
				{
					case XmlPullParser.START_TAG:
						if (tagname.equalsIgnoreCase("product"))
						{
							product = new Product();
						}
						break;

					case XmlPullParser.TEXT:
						text = parser.getText();
						//Log.e("-->",text);
						break;

					case XmlPullParser.END_TAG:
						if (tagname.equalsIgnoreCase("product"))
						{
							products.add(product);
						}
						else if (tagname.equalsIgnoreCase("image"))
						{
							product.setimage(text);
						}
						else if (tagname.equalsIgnoreCase("title"))
						{
							product.settitle(text);
						}						
						else if (tagname.equalsIgnoreCase("description"))
						{
							product.setdescription(text);
						}
						break;

					default:
						break;
				}
				eventType = parser.next();
			}

		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return products;
	}
}