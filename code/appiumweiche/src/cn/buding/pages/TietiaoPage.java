package cn.buding.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.lib.Action;

public class TietiaoPage {
	
	public  AndroidDriver driver;
	public  Action action;	
	
	public TietiaoPage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action =action;
	}
    /*
     * 贴条点
     * 高发    
     * 停车场    
     * 更多    
     *     
     */
	public static String tietiaoPoint = "cn.buding.martin:id/btn_ticket";
	public static String hotPoint = "cn.buding.martin:id/btn_rank";
	public static String parkings = "cn.buding.martin:id/btn_park";
	public static String moreContent = "cn.buding.martin:id/btn_more";
	
	/*
	 * 分享
	 */
	public static String shareButton = "cn.buding.martin:id/share";
	


}
