package com.jagex.launcher.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jagex.launcher.GameLaunch;

public class Logger { 
      
    /** 
     * The date format string. 
     */
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    
    /**
     * A byte array.
     */
    static byte[] bs = new byte[5];
    
    /** 
     * Print a log message. 
     * @param message 
     */
    public static void log(String message) {
        if (message == null) {
            return;
        }
        System.out.println("[" + dateFormat.format(new Date()) + "][" + GameLaunch.getSetting().getName() + "]: " + message); 
    } 
      
    /** 
     * Print a log message with class name. 
     * @param thread 
     * @param message 
     */
    public static void log(Class<?> thread, String message) {
        if (message == null) {
            return;
        }
        System.out.println("[" + dateFormat.format(new Date()) + "]["+Class.class.getSimpleName()+"]: "+message); 
    } 
      
    /** 
     * Print a log message with class name. 
     * @param thread 
     * @param message 
     */
    public static void log(String className, String message) {
        if (message == null) {
            return;
        }
        System.out.println("[" + dateFormat.format(new Date()) + "]["+className+"]: "+message); 
    } 
      
    /** 
     * Print a error message. 
     * @param message 
     */
    public static void error(String message) {
        if (message == null) {
            return;
        }
        System.err.println("[" + dateFormat.format(new Date()) + "][" + GameLaunch.getSetting().getName() +"]: "+message); 
    } 
      
    /** 
     * Print a error message with class name. 
     * @param thread 
     * @param message 
     */
    public static void error(Class<?> thread, String message) {
        if (message == null) {
            return;
        }
        System.err.println("[" + dateFormat.format(new Date()) + "]["+Class.class.getSimpleName()+"]: "+message); 
    } 
      
    /** 
     * Print a error message with class name. 
     * @param thread 
     * @param message 
     */
    public static void error(String className, String message) {
        if (message == null) {
            return;
        }
        System.err.println("[" + dateFormat.format(new Date()) + "]["+className+"]: "+message); 
    }

	public static void method69(byte[] bs2) {
		bs = bs2;
	}

	public static byte[] method71() {
		return bs;
	} 
} 
