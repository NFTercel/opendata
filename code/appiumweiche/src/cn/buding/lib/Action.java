package cn.buding.lib;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Action {
	
	public  AndroidDriver driver;
	public TouchAction touchaction;
	public Actions actions;
	private int timeout =20;
	

	
	//constructor init variable
//	@SuppressWarnings("static-access")
	public Action(AndroidDriver driver){
		this.driver = driver;
		touchaction = new TouchAction(driver);
		actions = new Actions(driver);
	}
	
	
	//extends click method
	public void click(By by){
		try{
			
			driver.findElement(by).click();
			
		}catch(Exception e){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			driver.findElement(by).click();
		}
		
	}
	
	/*
	 * 等比例点击
	 * @param denominator
	 */
	public  void constrainClick(int denominator){
		int x=driver.manage().window().getSize().width;
		int y=driver.manage().window().getSize().height;
		driver.tap(1, x/denominator, y/denominator, 0);
		}
	
	
	/**
	 * 
	 * @param startElement
	 * @param endElement
	 * @param Up 是否向上滑动
	 * 
	 */
	public void swipe(By startElement,By endElement,boolean Up){
		
		Point beginLoc;	
		WebElement start = null;
		try{
			 start = driver.findElement(startElement);		
		}catch(Exception e){
			
			//后续扩展
		}
		beginLoc = start.getLocation();	
		//调试使用
		System.out.println("start:" + beginLoc);
		WebElement end;
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MICROSECONDS);
		
		while(true){
			try{				
				end = driver.findElement(endElement);
				//调试使用
				System.out.println("end:" + end);
				break;
			}catch(Exception e){
				if(Up){
					driver.swipe(beginLoc.getX(), beginLoc.getY(), beginLoc.getX()+30, beginLoc.getY()+90, 4000);
				}else{
					driver.swipe(beginLoc.getX(), beginLoc.getY(), beginLoc.getX()-30, beginLoc.getY()-90, 4000);
				}
			}
			
		}
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);	
		
	}
	
	public void inputDataById(String id,String context){
		try{
		waitForRep(60);
		driver.findElementById(id).sendKeys(context);
//		sendKeys(context);
		Thread.sleep(1000);
		}catch(Exception e){
			System.out.println(id + " cannot be found,please check id!!!");
		}
	}
	
	/***
	* 左滑
	*/
	public  void slideRight(){
	int x= driver.manage().window().getSize().width;
	int y=driver.manage().window().getSize().height;
//	System.out.println("x:"+x+" y:"+y + x/4*1 + x/4*3);
	driver.swipe(x/5*4, y/2, x/5*1, y/2, 300);
	}
	
	/*下拉刷新
	 * 
	 */
	public void swipeDownRefresh(){
		int x= driver.manage().window().getSize().width;
		int y=driver.manage().window().getSize().height;
		driver.swipe(x/2, y/2, x/2, 4*y/5, 300);
	}
	
	/*
	 * viewgroup首次安装引导页的滑动
	 * 
	 */
	public  void viewgroupslide(int num) throws InterruptedException{
		for(int i =0;i<num;i++){
			Thread.sleep(1000);
			slideRight();
		}
	}
	
	/*
	 * http://www.2cto.com/kf/201501/368019.html
	 * http://www.51testing.com/html/44/15020244-1435135.html
	 */
	public  WebElement getViewByUiSelector(String name){
		return (WebElement) driver.findElementsByAndroidUIAutomator("new UiSelector().descriptionContains(\""+name+"\")");
	}
	
	
	
	//获得网络状态
	public  boolean checkNet(){
		String  status = driver.getNetworkConnection().toString();
		//网络状态
		System.out.println(status);
		if(status.contains("AirplaneMode: false")|status.contains("Wifi: true")|status.contains("Data: true")){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public void tap(By by){		
		touchaction.tap(driver.findElement(by)).release().perform();		
	}
	
	
	
	public void setValue(By by,String value){
		this.click(by);
		int size = driver.findElement(by).getText().length();
		driver.sendKeyEvent(123);//光标定位在最后
		for(int i =0;i<size;i++){
			driver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
		}
		    
		driver.findElement(by).sendKeys(value);
		
	}
	
	
	
	public void back(){
		//System.out.println("start1111");
		//driver.sendKeyEvent(AndroidKeyCode.BACK);
		//driver.sendKeyEvent(4);
		click(By.id("cn.buding.martin:id/back"));
	}
	
	public void categorySwipe(By source){
		
		WebElement start = driver.findElement(source);		
		Point beginLoc = start.getLocation();

		driver.swipe(beginLoc.getX(), beginLoc.getY(), beginLoc.getX()-700, beginLoc.getY(),8000);

	}
	
	
	
	public boolean isElementPresented(By by){
		boolean isDisplayed = false;
		
		try{
			isDisplayed = driver.findElement(by).isDisplayed();
		}catch(Exception e){
			isDisplayed = false;
		}
		return isDisplayed;
		
	}
	
	public void waitForElementPresent(By by){
		try{
			(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e){
			
		}
		
	}
	

	
	
	public void waitForElementIsEnable(By by){
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void waitFor(long timeout){
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForRep(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		
	} 
	
	//随机生成六位车牌号
    public String carString(){
      	String carInfoma = new String();
      	for (int i=0;i<6;i++){
      	   String buffer = "qwertyuiopasdfghjklzxcvbnm1234567890";
      	   char c = buffer.charAt((int) (Math.random()*36));
      	//   c=(char)(c+(int)(Math.random()*26));
      	   carInfoma += c;
      	  }
      	return carInfoma;
      }
    
    //生成随机手机号
    public String randomPhoneNumber(){
    	String randomNumber10 = new String();
    	String randomNumber11 = new String();
    	for (int i=0;i<9;i++){
    		String buffer = "1234567890";
    		char a = buffer.charAt((int)(Math.random()*10));
    		randomNumber10 += a;
    	}
    	randomNumber11 = "10"+randomNumber10;
    	return randomNumber11;
    }
    
    //地图放大
    public void zoom(){
    	int x = driver.manage().window().getSize().width/2;
    	int y = driver.manage().window().getSize().height/2;		
    	driver.zoom(x,y);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    	
    }
    //地图缩小
    public void pinch(){
    	int x = driver.manage().window().getSize().width/2;
    	int y = driver.manage().window().getSize().height/2;
    	driver.pinch(x,y);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    }
    
    //上滑
    public void swipeUp(){
    	int x = driver.manage().window().getSize().width;
    	int y = driver.manage().window().getSize().height;
    	driver.swipe(x/2, y/2+100, x/2, y/2-100, 300);
    }
    
    //滑动查找元素
    public void swipToSearch(String st){

    	if(driver.scrollTo("st").isEnabled()){
    		System.out.println(st+"存在");
    	}else{
    		System.out.println(st+"不存在");
    	}
    	
    }
    /*
     * 
     *截图
     */
    public void prtsc(String st){
    	File screen = driver.getScreenshotAs(OutputType.FILE);
		File screenFile;
		try {
			screenFile = new File("d:\\caseOutput\\"+st+".png");
			if(!screenFile.exists()){ 
				screenFile.createNewFile();
				}
			FileUtils.copyFile(screen, screenFile);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
    public void pinchPrtScr(String st){
        pinch();
      //缩小地图截图保存
		File screen = driver.getScreenshotAs(OutputType.FILE);
		File screenFile = new File("d:\\caseOutput\\tietiao"+st+".png");
		 try {
			FileUtils.copyFile(screen, screenFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
    }
    public void zoomPrtScr(String st){
    	zoom();
        //放大地图截图保存
  		File screen = driver.getScreenshotAs(OutputType.FILE);
  		File screenFile = new File("d:\\caseOutput\\tietiao"+st+".png");
  		 try {
  			FileUtils.copyFile(screen, screenFile);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    }
	
    public void isWebviewornot(){
		//判断是否有 WEBVIEW
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		System.out.println(contextName);
		if (contextName.contains("WEBVIEW")){
		driver.context(contextName);
		}else
		{
		System.out.println("no WEBVIEW"); 
		}
		}
    }
    
    /*
     * 
     * 比较两个图片相似度，满足返回true
     */
    public boolean sameAs(String myImage,String otherImage, double percent)
    {
    //BufferedImage otherImage = other.getBufferedImage();
    //BufferedImage myImage = getBufferedImage();
    	 BufferedImage image1 = null;
		 BufferedImage image2 = null;
		 File file1 = new File(myImage);
		 File file2 = new File(otherImage);
		 try {
			image1 = ImageIO.read(file1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			image2 = ImageIO.read(file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
    if (image2.getWidth() != image1.getWidth()) {
    return false;
    }
    if (image2.getHeight() != image1.getHeight()) {
    return false;
    }
    int[] otherPixel = new int[1];
    int[] myPixel = new int[1];
    int width = image1.getWidth();
    int height = image1.getHeight();
    int numDiffPixels = 0;
    for (int y = 0; y < height; y++) {
    for (int x = 0; x < width; x++) {
    if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
    numDiffPixels++;
    }
    }
    }
    double numberPixels = height * width;
    double diffPercent = numDiffPixels / numberPixels;
    return percent <= 1.0D - diffPercent;
    }
    
    /*
     * 得到子图
     */
    public BufferedImage getSubImage(BufferedImage image,int x, int y, int w, int h)
    {
    return image.getSubimage(x, y, w, h);
    }
    
    /*
     * 左滑控件
     */
    
    public void scrollElementToLeft(WebElement we){
    	int x = we.getLocation().x;
    	int y = we.getLocation().y;
    	int width = we.getSize().width;
    	int height = we.getSize().height;
    	int rightx = x+width;
    	int righty = y+height/2;
    	int leftx = x;
    	int lefty = righty;
    	driver.swipe(rightx-10, righty, leftx+10, lefty, 500);
    	
    }
    
    public void GetCpu() throws IOException {
        String str3=null;
        String str2=null;
        String str1=null;
        String str4=null;
          Runtime runtime = Runtime.getRuntime();
          Process proc = runtime.exec("adb shell dumpsys cpuinfo  $"+"cn.buding.martin");
          try {
              if (proc.waitFor() != 0) {
                  System.err.println("exit value = " + proc.exitValue());
              }
              BufferedReader in = new BufferedReader(new InputStreamReader(
                      proc.getInputStream()));
              StringBuffer stringBuffer = new StringBuffer();
              String line = null;
              while ((line = in.readLine()) != null) {
                  stringBuffer.append(line+" ");       
              }
          str1=stringBuffer.toString(); 
          str2=str1.substring(str1.indexOf("cn.buding.martin"),str1.indexOf("cn.buding.martin")+23);
        //  System.out.println(str2);
          str3=str2.substring(17,22); 
          //str4=str1.substring(str1.indexOf("cn.buding.martin")-6,str1.indexOf("cn.buding.martin")-1).trim();

          } catch (InterruptedException e) {
              System.err.println(e);
          }finally{
              try {
                  proc.destroy();
              } catch (Exception e2) {
              }
          }
          System.out.println("cpu占用率： ".concat(str3));
    }

    public String pid() throws IOException {
        String str3=null;
        String str2=null;
        String str1=null;
        String str4=null;
          Runtime runtime = Runtime.getRuntime();
          Process proc = runtime.exec("adb shell dumpsys cpuinfo  $"+"cn.buding.martin");
          try {
              if (proc.waitFor() != 0) {
                  System.err.println("exit value = " + proc.exitValue());
              }
              BufferedReader in = new BufferedReader(new InputStreamReader(
                      proc.getInputStream()));
              StringBuffer stringBuffer = new StringBuffer();
              String line = null;
              while ((line = in.readLine()) != null) {
                  stringBuffer.append(line+" ");       
              }
          str1=stringBuffer.toString(); 
          str2=str1.substring(str1.indexOf("cn.buding.martin")-10,str1.indexOf("cn.buding.martin"));
        //  System.out.println(str2);
          str3=str2.substring(1,4); 
          str4=str1.substring(str1.indexOf("cn.buding.martin")-6,str1.indexOf("cn.buding.martin")-1).trim();

          } catch (InterruptedException e) {
              System.err.println(e);
          }finally{
              try {
                  proc.destroy();
              } catch (Exception e2) {
              }
          }
          return str4;
    }
    public void GetMemory() throws IOException, InterruptedException {
          String str3=null;
          Runtime runtime = Runtime.getRuntime();
          Process proc = runtime.exec("adb shell dumpsys meminfo "+"cn.buding.martin");
          try {

              if (proc.waitFor() != 0) {
                  System.err.println("exit value = " + proc.exitValue());
              }
              BufferedReader in = new BufferedReader(new InputStreamReader(
                      proc.getInputStream()));
              StringBuffer stringBuffer = new StringBuffer();
              String line = null;
              while ((line = in.readLine()) != null) {
                  stringBuffer.append(line+" ");

              }
              String str1=stringBuffer.toString();
              String str2=str1.substring(str1.indexOf("Objects")-60,str1.indexOf("Objects"));     
              str3 = str2.substring(0,10);
              str3.trim();
          } catch (InterruptedException e) {
              System.err.println(e);
          }finally{
              try {
                  proc.destroy();
              } catch (Exception e2) {
              }
          }
        System.out.println("内存占用："+ str3 +"kB");
    }

     public double getFlow() throws IOException{
    	 double downFlowSize=0;
         String Pid= pid();
     try{
         Runtime runtime = Runtime.getRuntime();
         Process proc = runtime.exec("adb shell cat /proc/"+Pid+"/net/dev");
         try {
             if (proc.waitFor() != 0) {
                 System.err.println("exit value = " + proc.exitValue());
             }
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     proc.getInputStream()));
             StringBuffer stringBuffer = new StringBuffer();
             String line = null;
             while ((line = in.readLine()) != null) {
                 stringBuffer.append(line+" ");


             }
         String str1=stringBuffer.toString();
         String str2=str1.substring(str1.indexOf("wlan0:"),str1.indexOf("wlan0:")+90);
         //System.out.println(str2);
         String str4=str2.substring(7,16);
         str4 = str4.trim();
         int Flow=Integer.parseInt(str4);
         downFlowSize=Flow/1024;

         } catch (InterruptedException e) {
             System.err.println(e);
         }finally{
             try {
                 proc.destroy();
             } catch (Exception e2) {
             }
         }
     }
         catch (Exception StringIndexOutOfBoundsException)
         {

         }

         return downFlowSize;
     }
     
     /**获得apk绝对路径
      * 
      * 
      */
     public String obtainAbsolutePath(){
    	 String temp = System.getProperty("user.dir");
    	 String apkPathtemp = temp.concat("\\apps\\weiche\\weiche381.apk");
    	 String apkPath = apkPathtemp.replaceAll("\\\\", "/");
    	 return apkPath;
     }
     
     public void clickweijianghu() throws InterruptedException{
    	 driver.findElementByName("微生活").click();
    	 int weijianghuid = 0;
			List <WebElement> channels = driver.findElementsById("cn.buding.martin:id/service_title");
         for(int i = 0;i<channels.size();i++){
         	if(channels.get(i).getText().equals("微车驿站")){
         		weijianghuid = i;
         	}
         }
         Thread.sleep(3000);
         channels.get(weijianghuid).click();
         Thread.sleep(3000);
     }
     
     public void clickweizhangInlife() throws InterruptedException{
    	 int x = driver.manage().window().getSize().width;
    	 int y = driver.manage().window().getSize().height;
    	 driver.swipe(x/2, 4*y/5, x/2, 1*y/5, 500);
    	 Thread.sleep(3000);
    	 driver.swipe(x/2, 4*y/5, x/2, 1*y/5, 500);
    	 driver.scrollTo("违章缴费").click();
     }
     
     public void backToHomepage(){
    	 driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
    	 assertEquals(1, 2);
     }
     //重置app
     public void resetApp(){
    	 driver.resetApp();
    	 driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
     }
     //覆盖安装
     public void coverInstall() throws InterruptedException{
    	 try {
				Runtime.getRuntime().exec("adb install -r "+obtainAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 Thread.sleep(20000);
     }
     
     //修改系统时间
     public void testDate(){
    	 try {
    	 Process process = Runtime.getRuntime().exec("su");
    	 String datetime="20131023.112800"; //测试的设置的时间【时间格式 yyyyMMdd.HHmmss】
    	 DataOutputStream os = new DataOutputStream(process.getOutputStream());
    	 os.writeBytes("setprop persist.sys.timezone GMT\n");
    	 os.writeBytes("/system/bin/date -s "+datetime+"\n");
    	 os.writeBytes("clock -w\n");
    	 os.writeBytes("exit\n");
    	 os.flush();
    	 } catch (IOException e) {
    	 e.printStackTrace();
    	 }
    	 }
     
}

