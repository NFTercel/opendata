package cn.buding.main;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import cn.buding.lib.AppiumBase;
import cn.buding.variable.GlobalDataStore;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Share extends AppiumBase {
	
	@Test
	public void test001entryHome() throws Exception{
		System.out.println("**********test001**********");
		try {
			vgp.entryHome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}
	
	@Test
	public void test002wechatLogin() throws InterruptedException{
		try {
			hp.login();
			sp.EntryWechatAccount();
			//断言
			hp.compareCars(GlobalDataStore.vehicle_wechatuser);
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}
	
	 /*文章分享（朋友圈）
     * 取消
     */
    @Test
    public void test081articleShare() throws InterruptedException{
    	System.out.println("**********test081**********");
    	try {
			action.clickweijianghu();
			//点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById("cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			//分享朋友圈
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			//点击返回
			action.click(By.id("com.tencent.mm:id/ew"));
			action.click(By.id("com.tencent.mm:id/axw"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    /*文章成功分享
     * 
     */
    @Test
    public void test082articleShareSucc() throws InterruptedException{
    	System.out.println("**********test082**********");
    	//进入微江湖
    	try {
			action.clickweijianghu();
			//点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById("cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			//分享朋友圈
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			//点击发送
			action.click(By.id("com.tencent.mm:id/eb"));
			System.out.println(driver.findElementById("cn.buding.martin:id/share").getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    /*分享点击蒙版
     * 
     */
    @Test
    public void test083mengban() throws InterruptedException{
    	System.out.println("**********test083**********");
    	try {
			action.clickweijianghu();
			//点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById("cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			Thread.sleep(3000);
			action.constrainClick(2);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    
    /*账户与设置页的分享
     * 取消
     */
    @Test
    public void test084settingpageShare() throws InterruptedException{
    	System.out.println("**********test084**********");
    	try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/ew"));
			action.click(By.id("com.tencent.mm:id/axw"));
			Thread.sleep(2000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    	
    }
    /*账户与设置页的分享
     * 成功分享
     */
    @Test
    public void test085settingpageShare() throws InterruptedException{
    	System.out.println("**********test085**********");
    	try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    /*账户与设置页的分享
     * 点击蒙版取消
     */
    @Test
    public void test086settingpageShare() throws InterruptedException{
    	System.out.println("**********test086**********");
    	try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			Thread.sleep(2000);
			action.constrainClick(2);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    
    /*违章详情页分享
     * 
     */
    @Test
    public void test091vioDetailShare() throws InterruptedException{
    	System.out.println("**********test091**********");
    	try {
    		hp.login();
    		sp.EntryBindingAccount();
			action.click(By.name("津AHJ236"));
			action.click(By.id("cn.buding.martin:id/name"));
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    	
    }
    /*贴条地图分享
     * 
     */
    @Test
    public void test092tietiaoShare() throws InterruptedException{
    	System.out.println("**********test092**********");
    	try {
			action.click(By.name("贴条地图"));
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
    }
    
    /*在路上详情页分享
     * 
     */
    @Test
    public void test093onroadShare() throws InterruptedException{
    	System.out.println("**********test093**********");
    	try {
    		hp.openOnroad();
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			action.click(By.id("cn.buding.martin:id/share"));
			//分享日汇总及详情
			action.click(By.id("cn.buding.martin:id/share_day_detail"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			//仅分享日汇总
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/share_day_summary"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
		
    }
	@Test
	public void test101deleteApp(){
		driver.removeApp("io.appium.unlock");
		driver.removeApp("io.appium.settings");
		driver.removeApp("io.appium.android.ime");
		
	}
	
	
	
	@AfterClass
	public  static void tearDown() throws Exception {
		driver.quit();
	}
}
