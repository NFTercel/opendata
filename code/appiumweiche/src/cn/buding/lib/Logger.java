package cn.buding.lib;

import cn.buding.variable.DebugInfoStore;
import android.util.Log;

public class Logger {

	// public static String TAG = "--Martin with Appium test tag--";
	private static String TAG = DebugInfoStore.TAG;

	// define constructor
	private Logger() {

	}

	// Verbose log is lowest priority
	public static void v(String msg) {
		Log.v(TAG, msg);
	}

	public static void v(String msg, Throwable tr) {
		Log.v(TAG, msg, tr);
	}

	// Debug log
	public static void d(String msg) {
		Log.d(TAG, msg);
	}

	public static void d(String msg, Throwable tr) {
		Log.d(TAG, msg, tr);
	}

	// INFO log
	public static void i(String msg) {
		Log.i(TAG, msg);
	}

	public static void i(String msg, Throwable tr) {
		Log.i(TAG, msg, tr);
	}

	// Warning log
	public static void w(String msg) {
		Log.w(TAG, msg);
	}

	public static void w(Throwable tr) {
		Log.w(TAG, tr);
	}

	public static void w(String msg, Throwable tr) {
		Log.w(TAG, msg, tr);
	}

	// Error log
	public static void e(String msg) {
		Log.e(TAG, msg);
	}

	public static void e(String msg, Throwable tr) {
		Log.e(TAG, msg, tr);
	}
}
