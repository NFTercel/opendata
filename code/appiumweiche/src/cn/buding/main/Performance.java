package cn.buding.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Performance {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
          Performance pe = new Performance();
          double initflow = pe.getFlow();
    	  double sum = 0;
          for(int i = 0; i<=5; i++){
        	  pe.GetCpu();
        	  pe.GetMemory();
        	  Thread.sleep(3000);
        	  double currentflow = pe.getFlow();
        	  System.out.println("下载流量："+(currentflow-initflow)+"KB");
        	  sum += currentflow-initflow;
        	  initflow = currentflow;
          }
          System.out.println("总流量："+sum+"KB");
	}
	public void GetCpu() throws IOException {
        String str3=null;
        String str2=null;
        String str1=null;
        String str4=null;
        String str5=null;
        String str6=null;
          Runtime runtime = Runtime.getRuntime();
          Process proc = runtime.exec("adb shell dumpsys cpuinfo  "+"cn.buding.martin");
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
          //System.out.println(str2);
          str2=str1.substring(str1.indexOf("cn.buding.martin"),str1.indexOf("cn.buding.martin")+23);
          //System.out.println(str2);
          str3=str2.substring(17,str2.indexOf("%")+1); 
          str4=str1.substring(str1.indexOf("cn.buding.martin")-6,str1.indexOf("cn.buding.martin")-1).trim();
          str5=str1.substring(str1.indexOf("cn.buding.martin")-13,str1.indexOf("cn.buding.martin")-1).trim();
          str6=str5.substring(0, str5.indexOf("%")+1);
          } catch (InterruptedException e) {
              System.err.println(e);
          }finally{
              try {
                  proc.destroy();
              } catch (Exception e2) {
              }
          }
          //System.out.println("cpu占用率:".concat(str2));
          System.out.println("cpu占用率: ".concat(str6));
          //System.out.println("cpu占用率:".concat(str3));
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
          str3=str2.substring(str2.indexOf("%")+1,str2.indexOf("/")).trim(); 
          str4=str1.substring(str1.indexOf("cn.buding.martin")-6,str1.indexOf("cn.buding.martin")-1).trim();
          
          } catch (InterruptedException e) {
              System.err.println(e);
          }finally{
              try {
                  proc.destroy();
              } catch (Exception e2) {
              }
          }
          
          return str3;
    }
//	public void GetMemory() throws IOException, InterruptedException {
//        String str3=null;
//        Runtime runtime = Runtime.getRuntime();
//        Process proc = runtime.exec("adb shell dumpsys meminfo "+"cn.buding.martin");
//        try {
//
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line+" ");
//
//            }
//            String str1=stringBuffer.toString();
//            String str2=str1.substring(str1.indexOf("Objects")-60,str1.indexOf("Objects"));     
//            str3 = str2.substring(0,10);
//            str3.trim();
//        } catch (InterruptedException e) {
//            System.err.println(e);
//        }finally{
//            try {
//                proc.destroy();
//            } catch (Exception e2) {
//            }
//        }
//      System.out.println("内存占用: "+ Integer.parseInt(str3.trim())/1024+ "m");
//  }

	public void GetMemory() throws IOException, InterruptedException {
        String str3=null;
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("adb shell dumpsys meminfo cn.buding.martin|grep Dalvik");
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
            String str2=str1.substring(str1.indexOf("Dalvik Heap"),str1.indexOf("Dalvik Heap")+65);     
            str3 = str2.substring(50,56).trim();
            //str3.trim();
//            System.out.println(str2);
//            System.out.println(str3);
        } catch (InterruptedException e) {
            System.err.println(e);
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
      System.out.println("内存占用: "+ Integer.parseInt(str3)/1024+ "M");
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
}
