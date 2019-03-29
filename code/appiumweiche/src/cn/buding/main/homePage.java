package cn.buding.main;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.NetworkConnectionSetting;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.junit.runners.MethodSorters;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager.WifiLock;
import cn.buding.lib.AppiumBase;
import cn.buding.pages.HomePage;
import cn.buding.variable.GlobalDataStore;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class homePage extends AppiumBase{
	
	@Test
	public void test001entryHome() throws Exception{
		vgp.entryHome();
		
	}
	
	

    /*文章成功分享
     * 
     */
    @Test
    public void test082articleShareSucc() throws InterruptedException{
    	System.out.println("**********test008**********");
    	try {
    		hp.login();
			driver.runAppInBackground(5);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		} 
    }
	
		
		@Test
		public void test150deleteApp(){
			driver.removeApp("io.appium.unlock");
			driver.removeApp("io.appium.settings");
			driver.removeApp("io.appium.android.ime");
			
		}
		
		
		
		@AfterClass
		public  static void tearDown() throws Exception {
			driver.quit();
		}
		
}
