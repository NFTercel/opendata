package cn.buding.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.lib.Action;
import cn.buding.variable.GlobalDataStore;


public class SettingPage {
	
public  AndroidDriver driver;
public  Action action;	


	public SettingPage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action =action;
	}
	
	/*
	 * 账户与设置
	 */
	public static String account = "cn.buding.martin:id/login";
	
	/*
	 * 登陆界面
	 * 手机号
	 * 密码
	 * 注册
	 * 登陆
	 * 忘记密码
	 * 微信登陆
	 * 退出登录
	 * 确定按钮
	 * 修改密码
	 * 旧密码
	 * 新密码
	 * 切换城市
	 * 关于我们
	 * 版本
	 * 在路上每日通知
	 */
	public static String phonenum = "cn.buding.martin:id/phone";
	public static String password = "cn.buding.martin:id/password";
	public static String registerbytext = "注册";
	public static String registerButton = "cn.buding.martin:id/register";
	public static String loginbytext = "登录";
	public static String forgetpassword = "cn.buding.martin:id/reset_password";
	public static String weixinicon = "cn.buding.martin:id/btn_login_weixin";
	public static String registerbyxpath = "//android.widget.LinearLayout[2]/descendant::android.widget.TextView[@text='注册']";
	public static String loginout = "cn.buding.martin:id/logout";
	public static String certainKnow = "cn.buding.martin:id/btn_text";
	public static String changePassword = "cn.buding.martin:id/change_password";
	public static String oldpassword = "cn.buding.martin:id/et_old_password";
	public static String newpassword = "cn.buding.martin:id/et_password";
	public static String changeCity = "cn.buding.martin:id/city";
	public static String aboutus = "cn.buding.martin:id/about";
	public static String version = "cn.buding.martin:id/version";
	public static String onroadPush = "cn.buding.martin:id/onroad_push_dialy";
	public static String onroadDialyCheck = "cn.buding.martin:id/onroad_push_dialy_check";
	//登陆成功toast
	public static String loginToast = "登陆成功";
	

	public  void EntryAccount() throws InterruptedException{
		action.waitForRep(60);
		System.out.println(" 登陆手机账号13683116962");
		action.click(By.id(account));
		Thread.sleep(1000);
//		System.out.println(driver.getPageSource());
		//name:为空间的text属性
//		AppiumBase.action.click(By.name(loginbytext) );
		action.inputDataById(phonenum, GlobalDataStore.IPhone_num);
		action.inputDataById(password, GlobalDataStore.Password);
        List <WebElement> denglu = driver.findElementsByName("登录");
        denglu.get(1).click();
		Thread.sleep(2000);
		//判断是否登录
	    if(driver.findElementById(loginout).isDisplayed()){
	    	  System.out.println("用户已登录");
	      }else{
	    	  System.out.println("用户未登录");
	      }
		//返回首页
		action.back();
		
		
		/*
		 * 滚动查找数据
		 * driver.scrollTo(loginToast);
		 */
		
		/*
		 * 测试toast
		 * 后续解决toast测试
		 */
//		driver.findElement(By.linkText(loginToast));
		

	}
	
	
	public  void EntryBindingAccount() throws InterruptedException{
		action.waitForRep(60);
		System.out.println(" 登陆绑定账号");
		action.click(By.id(account));
		Thread.sleep(1000);
//		System.out.println(driver.getPageSource());
		//name:为空间的text属性
//		AppiumBase.action.click(By.name(loginbytext) );
		action.inputDataById(phonenum, "13810276832");
		action.inputDataById(password, "qwerty");
		List <WebElement> denglu = driver.findElementsByName(loginbytext);
		denglu.get(1).click();
		Thread.sleep(2000);
		//返回首页
	      if(null==By.id(loginout)){
	    	  System.out.println("用户未登录");
	      }else{
	    	  System.out.println("用户已登录");
	      }
		action.back();
		action.waitForRep(60);
		
		
		/*
		 * 滚动查找数据
		 * driver.scrollTo(loginToast);
		 */
		
		/*
		 * 测试toast
		 * 后续解决toast测试
		 */
//		driver.findElement(By.linkText(loginToast));
		

	}
	
	public void entryDiyBackAccount() throws InterruptedException{
		action.click(By.id(account));
		Thread.sleep(1000);
//		System.out.println(driver.getPageSource());
		//name:为空间的text属性
//		AppiumBase.action.click(By.name(loginbytext) );
		action.inputDataById(phonenum, "12345678910");
		action.inputDataById(password, "123456");
		List <WebElement> denglu = driver.findElementsByName(loginbytext);
		denglu.get(1).click();
		Thread.sleep(2000);
		//返回首页
	      if(null==By.id(loginout)){
	    	  System.out.println("用户未登录");
	      }else{
	    	  System.out.println("用户已登录");
	      }
		action.back();
		action.waitForRep(60);
	}
	/*
	 * 
	 * 
	 * 登录微信账号
	 */
	public  void EntryWechatAccount() throws InterruptedException{
		action.click(By.id(account));
		//微信确认登陆按钮
		action.click(By.id("cn.buding.martin:id/btn_login_weixin"));
		Thread.sleep(7000);
		//获得webview的坐标
		int x = driver.findElementByClassName("com.tencent.smtt.webkit.WebView").getSize().width;
		int y = driver.findElementByClassName("com.tencent.smtt.webkit.WebView").getSize().height;
		//action.tap(by);
		int x0 = driver.findElementByClassName("com.tencent.smtt.webkit.WebView").getLocation().x;
		int y0 = driver.findElementByClassName("com.tencent.smtt.webkit.WebView").getLocation().y;
		//点击登陆
		//action.click(By.className("android.widget.Button"));
		driver.tap(1, x0+x/2, y0+y/2, 1);
		Thread.sleep(7000);
		action.back();
	}
	
	/*登录无车辆账号
	 * 
	 */
	public void entryNocarAccount() throws InterruptedException{
		System.out.println(" 登陆手机账号12633116962");
		action.click(By.id(account));
		Thread.sleep(1000);
//	System.out.println(driver.getPageSource());
		//name:为空间的text属性
//	AppiumBase.action.click(By.name(loginbytext) );
		action.inputDataById(phonenum, "12633116962");
		action.inputDataById(password, "qwerty");
		List <WebElement> denglu = driver.findElementsByName("登录");
		denglu.get(1).click();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		Thread.sleep(2000);
		//判断是否登录
		if(driver.findElementById(loginout).isDisplayed()){
			  System.out.println("用户已登录");
		  }else{
			  System.out.println("用户未登录");
		  }
		//返回首页
		action.back();
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 注册新账号*/
	public void RegisterAccount() throws InterruptedException{
		action.waitForRep(60);
		action.click(By.id(account));
		Thread.sleep(1000);
		action.click(By.name("注册"));
		action.inputDataById(phonenum, action.randomPhoneNumber());
		action.inputDataById(password, "111111");
		action.click(By.id(registerButton));
		assertEquals(driver.findElementById("cn.buding.martin:id/title").getText(), "注册成功");
		action.click(By.id(certainKnow));
		action.back();//回到主页
		
		
		
	}
	
	/*
	 * 
	 * 退出登录*/
	public void LogOut() throws InterruptedException{
		action.click(By.id(loginout));
		action.click(By.id("android:id/button1"));
		Thread.sleep(5000);
		if(driver.currentActivity().equals(".activity.profile.MoreActivity")){
		action.back();
		}else{
			Thread.sleep(5000);
			action.back();
		}
	}
	
	/*
	 * 
	 * 修改密码*/
	public void resetPasscode(String a, String b){
        action.click(By.id(changePassword));
        action.inputDataById(oldpassword, a);
        action.inputDataById(newpassword, b);
        List<WebElement> we =driver.findElementsByName("修改密码");
        we.get(1).click();
        action.click(By.id(certainKnow));
	}
	
	/*
	 * 
	 * 切换城市*/
	public void citySwitch(String a){
		action.click(By.id(changeCity));
		action.click(By.name(a));
		action.back();
		if(driver.currentActivity().equals(".activity.profile.MoreActivity")){
			action.back();
				}
	}

	
	public void checkAboutUs() throws InterruptedException{
		driver.scrollTo("其他").click();
		action.click(By.id(aboutus));
		System.out.println(driver.findElementById(version).getText());
		//回到账户与设置页面
		action.back();
		Thread.sleep(1000);
		action.back();
		Thread.sleep(500);
		action.back();
		
	}
	
	public void onroadDialyPushCheck() throws InterruptedException{
		if(driver.findElementById(onroadPush).isEnabled()){
			System.out.println("在路上已经打开");	
		}else{
			action.back();
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			action.waitForRep(600);
			action.swipeUp();
			action.waitForRep(600);
			action.swipeUp();
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(2000);
			action.constrainClick(2);
			Thread.sleep(1000);
			action.back();
			action.click(By.id("cn.buding.martin:id/more"));
			if(driver.findElementById(onroadDialyCheck).getAttribute("checked").equals("true")){
				System.out.println("在路上每日通知已打开");
			}else{
				System.out.println("在路上每日通知未打开");
			}
		}
		action.back();
		
	}
	//读取短信临时码
	public String readVerifyCode() throws InterruptedException{
		action.click(By.id(account));
		action.click(By.id("cn.buding.martin:id/reset_password"));
		action.inputDataById("cn.buding.martin:id/phone", "13810276832");
		action.click(By.id("cn.buding.martin:id/ok_text"));
		Thread.sleep(20000);
		driver.startActivity("com.android.contacts", "com.android.mms.ui.ConversationList");
		driver.findElementByName("106909995666").click();
		String str = driver.findElementById("com.android.contacts:id/text_view").getText();
		str = str.trim();		
		String str1 = "";
        if(str1!=null && !"".equals(str)){
        	for(int i=0;i<str.length();i++){
        		if(str.charAt(i)>48&&str.charAt(i)<=57){
        			str1 += str.charAt(i);
        		}
        	}
        }
        driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
        return str1;
	}
	/*
	 * 
	 * 通过短信码登录*/
	public void entryBySMS(String sms){
        action.click(By.id(account));
		action.inputDataById("cn.buding.martin:id/phone","13810276832");
		action.inputDataById("cn.buding.martin:id/password",sms);
		action.click(By.name("登录"));
		 if(null==By.id("cn.buding.martin:id/logout")){
	    	  System.out.println("用户未登录");
	      }else{
	    	  System.out.println("用户已登录");
	      }
		action.back();
		action.waitForRep(100);
		
	}
	
	/*意见反馈
	 * 
	 * 
	 */
	public void giveFeedback() throws InterruptedException{
		driver.scrollTo("其他").click();
		action.click(By.id("cn.buding.martin:id/feedback"));
		action.inputDataById("cn.buding.martin:id/content","测试测试");
		action.inputDataById("cn.buding.martin:id/contact","111111");
		action.click(By.name("发送"));
		Thread.sleep(2000);
		assertEquals(driver.currentActivity(), ".activity.profile.OtherActivity");
		action.back();
		Thread.sleep(1000);
		action.back();
	}

}
