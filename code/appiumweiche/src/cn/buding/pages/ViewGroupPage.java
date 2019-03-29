package cn.buding.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

import cn.buding.lib.Action;
import io.appium.java_client.android.AndroidDriver;

public class ViewGroupPage {
	public AndroidDriver driver;
	public Action action;
	
	public ViewGroupPage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action = action;
	}
	public static String cities = "cn.buding.martin:id/tv_name";
	public static String startUsing = "cn.buding.martin:id/start";
	
	
	public void entryHome() throws InterruptedException{
        if(action.checkNet() != true){
			action.waitForRep(60);
			action.click(By.name("北京(北京)"));
			Thread.sleep(1000);
		}else{
			Thread.sleep(4000);
			if(driver.currentActivity().equals(".activity.ChooseSelectedCity")){
				action.click(By.name("北京(北京)"));
			}
		}
		
		//滑动viewgroup
        Thread.sleep(2000);
		action.viewgroupslide(3);

		//进入微车主页
		action.waitForRep(60);
		action.click(By.id(startUsing));
		Thread.sleep(2000);
		action.constrainClick(4);
		assertEquals((driver.currentActivity()),".activity.butterfly.ButterflyActivity");
		Thread.sleep(2000);
	}
}
