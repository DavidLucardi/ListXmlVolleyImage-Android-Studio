package com.msnegi.listxmlvolley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	ListView listView;
	List<Product> product = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		
		try
		{
	        new HttpHandler() 
	        {
	            @Override
	            public HttpUriRequest getHttpRequestMethod() 
	            {
	                return new HttpGet("http://www.msnegi.com/temp/ListXml/data.xml");
	            }
	            
	            @Override
	            public void onResponse(String result) 
	            {
	                Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

	    			XMLPullParserHandler parser = new XMLPullParserHandler();
	    			product = parser.parse(result);	 
	    			
	    			CustomBaseAdapter adapter = new CustomBaseAdapter(getApplicationContext(), product);
	    					
	    			listView.setAdapter(adapter);	    			
	            }

	        }.execute();
		}
		catch(Exception exp)
		{}
	        		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
