package com.msnegi.listxmlvolley;

public class Product
{
	private String image;
	private String title;	
	private String description = "";

	public void setimage(String image)
	{
		this.image = image;
	}

	public String getimage()
	{
		return image;
	}

	public void settitle(String title)
	{
		this.title = title;
	}

	public String gettitle()
	{
		return title;
	}	

	public void setdescription(String description)
	{
		this.description = description;
	}

	public String getdescription()
	{
		return description;
	}
}