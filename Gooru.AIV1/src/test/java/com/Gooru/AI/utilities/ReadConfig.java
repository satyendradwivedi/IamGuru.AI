package com.Gooru.AI.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	 public String getProperty(String key) {
	        return pro.getProperty(key);
	    }
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	public String getApplicationRegistrationURL()
	{
		String Regitrationurl=pro.getProperty("baseURLRegister");
		return Regitrationurl;
	}
	public String getTempemailURL()
	{
		String emailurl=pro.getProperty("tempemailurl");
		return emailurl;
	}
	
	//set 1 admin user
	public String getEmail()
	{
	String email=pro.getProperty("email");
	return email;
	}
	
	public String getPassword()
	{
	String password=pro.getProperty("password");
	return password;
	}
	//set invalid
			public String getusr2()
			{
			String user2=pro.getProperty("user2");
			return user2;
			}
			
			public String getpass2()
			{
			String gpass2=pro.getProperty("pass2");
			return gpass2;
			}
	//set glogin
		public String getGemail()
		{
		String gemailtext=pro.getProperty("gemail");
		return gemailtext;
		}
		
		public String getGPassword()
		{
		String gpassword=pro.getProperty("gpassword");
		return gpassword;
		}
	//Registration#1
	public String getFirstName()
	{
	String fname=pro.getProperty("FirstName");
	return fname;
	}
	public String getLastName()
	{
	String lname=pro.getProperty("LastName");
	return lname;
	}
	public String getREmail()
	{
	String remail=pro.getProperty("Remail");
	return remail;
	}
	public String getRpassword()
	{
	String rpassword=pro.getProperty("Rpassword");
	return rpassword;
	}
	public String getRCpassword()
	{
	String rcpassword=pro.getProperty("RCpassword");
	return rcpassword;
	}
	public String getRdob()
	{
	String rdob=pro.getProperty("dob");
	return rdob;
	}
	public String getLearnerBio()
	{
	String learnerBio=pro.getProperty("LearnerBio");
	return learnerBio;
	}
	public String getPlatformName()
	{
	String pName=pro.getProperty("PlatformName");
	return pName;
	}
	public String getPlatformurl()
	{
	String pUrl=pro.getProperty("PlatformUrl");
	return pUrl;
	}
	public String getQuality()
	{
	String quality=pro.getProperty("Quality");
	return quality;
	}
	
	
	public String getRPhone()
	{
	String rphone=pro.getProperty("phone");
	return rphone;
	}
	
	//ResetPassword
	public String getResetnewpassword()
	{
	String resetnewpassword=pro.getProperty("usernewpassword");
	return resetnewpassword;
	}
	public String getResetConfirmnewpassword()
	{
	String resetconfirmnewpassword=pro.getProperty("userconfirmpassword");
	return resetconfirmnewpassword;
	}
	
	//Post
	public String getTile()
	{
	String title=pro.getProperty("postTitle");
	return title;
	}
	public String getDescription()
	{
	String description=pro.getProperty("postDescription");
	return description;
	}
	//Compaign#2
	public String getDate()
	{
	String date=pro.getProperty("date");
	return date;
	}
	public String getHour()
	{
	String hour=pro.getProperty("hour");
	return hour;
	}
	public String getMinute()
	{
	String minute=pro.getProperty("minute");
	return minute;
	}
	public String getChromePath()
	{
	String chromepath=pro.getProperty("chromepath");
	return chromepath;
	}
	
	public String getIEPath()
	{
	String iepath=pro.getProperty("iepath");
	return iepath;
	}
	
	public String getFirefoxPath()
	{
	String firefoxpath=pro.getProperty("firefoxpath");
	return firefoxpath;
	}
//Gooru Profile Data
	public String genetation()
	{
		String generation=pro.getProperty("Generation");
		return generation;
	}
	
	public String goorutitle()
	{
		String goorutitle=pro.getProperty("GooruTitle");
		return goorutitle;
	}
	//Course Management
	public String getCourseTitle()
	{
		String courseTitle=pro.getProperty("CourseName");
		return courseTitle;
	}
	public String getCourseDescription()
	{
		String courseDescription=pro.getProperty("CourseDescription");
		return courseDescription;
	}
}