package cn.buding.lib;

import org.junit.BeforeClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.pages.AddCarPage;
import cn.buding.pages.LifePage;
import cn.buding.pages.SettingPage;
import cn.buding.pages.TietiaoPage;
import cn.buding.pages.ViewGroupPage;
import cn.buding.pages.HomePage;
import cn.buding.pages.ViolateDetailPage;



public  class AppiumBase {
	
	public static Action action;
	public static ViewGroupPage vgp;
	public static HomePage hp;

	public static SettingPage sp;
	public static AddCarPage ac;
	public static TietiaoPage tt;
	public static BMPLoader bmp;
	public static ViolateDetailPage vdp;
	public static LifePage lp;
	
	
	public static AndroidDriver driver;
	
	
	@BeforeClass
	public static void TestAndroidSettings() throws MalformedURLException{
		
		GetConfiguration td = new GetConfiguration(System.getProperty("user.dir")+"/src/cn/buding/variable/gobalsettings.csv");
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName",td.getTestData("deviceName", "TC001") );
		cap.setCapability("ignoreUnimportantViews",td.getTestData("ignoreUnimportantViews", "TC001") );
		cap.setCapability("newCommandTimeout",td.getTestData("newCommandTimeout", "TC001") );
		cap.setCapability("app",GetConfiguration.getApp("/apps/weiche/weiche381.apk"));
		cap.setCapability("appPackage", "cn.buding.martin");
        cap.setCapability("appActivity", ".activity.SplashActivity");
        cap.setCapability("unicodeKeyboard", "True");
        cap.setCapability("resetKeyboard", "True");
        //取消appium对apk重签名
        cap.setCapability("noSign", true);
        //cap.setCapability("automationName", "selendroid");
        
        //driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), cap);
        driver = new AndroidDriver(new URL("http://"+td.getTestData("server", "TC001")+"/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(td.getTestData("timeout", "TC001")), TimeUnit.MILLISECONDS);

//       System.out.println("初始化");
         action = new Action(driver);
		
		vgp = new ViewGroupPage(driver,action);	
		hp = new HomePage(driver,action);
		sp = new SettingPage(driver,action);
		ac = new AddCarPage(driver,action);
		lp = new LifePage(driver, action);
		vdp = new ViolateDetailPage(driver, action);
		

	}
	
	


}
