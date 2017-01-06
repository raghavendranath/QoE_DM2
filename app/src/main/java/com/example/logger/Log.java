package com.example.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.ragha.qoe_dm.MainActivity;

import android.os.Environment;

public class Log {
    private static final String NEW_LINE =  System.getProperty("line.separator") ;
    public static boolean mLogcatAppender = true;
    static File mLogFile = null;
    static File path;
    static {
        try {
             path = new File(Environment.getRootDirectory()+"/QoE_DAILYMOTION_LOGS");
            if (!path.exists()) {

                path.mkdir();

            }

            mLogFile = new File(path.getAbsolutePath()//folder path
                    + File.separator
                    + "QoE_DM_logcat_" + MainActivity.time + ".txt" ); //file name
            mLogFile.createNewFile();
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
       }
        //logDeviceInfo();


    public static void i( String TAG, String message ) throws IOException{
        //appendLog( TAG + " : " + message );
        if ( mLogcatAppender ) {
            android.util.Log.i( TAG, message );
        }
    }

    public static void d( String TAG, String message ) throws IOException{
        appendLog( TAG + " : " + message );
        if ( mLogcatAppender ) {
            android.util.Log.d( TAG, message );
        }
    }

    public static void e( String TAG, String message ) throws IOException{
        //appendLog( TAG + " : " + message );
        if ( mLogcatAppender ) {
            android.util.Log.e( TAG, message );
        }
    }

    public static void v(String TAG, String message ) throws IOException{
        //appendLog(TAG + " : " + message);
        if ( mLogcatAppender ) {
            android.util.Log.v( TAG, message );
        }
    }

    public static void w( String TAG, String message ) throws IOException {
       // appendLog( TAG + " : " + message );
        if ( mLogcatAppender ) {
            android.util.Log.w( TAG, message );
        }
    }

    private static synchronized void appendLog( String text ) throws IOException {
        if(text.contains("Cannot")){
            return;
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        mLogFile = new File(path.getAbsolutePath()//folder path
                + File.separator
                + "QoE_DM_logcat_" + MainActivity.time + ".txt" ); //file name
        mLogFile.createNewFile();
        try {
            final FileWriter fileOut = new FileWriter( mLogFile, true );
            fileOut.append( sdf.format(new Date()) + " : " + text + NEW_LINE );
            fileOut.close();
        }
        catch ( final IOException e ) {
            e.printStackTrace();
        }
    }

    private static void logDeviceInfo() throws IOException{
        appendLog("Model : " + android.os.Build.MODEL);
        appendLog("Brand : " + android.os.Build.BRAND);
        appendLog("Product : " + android.os.Build.PRODUCT);
        appendLog("Device : " + android.os.Build.DEVICE);
        appendLog("Codename : " + android.os.Build.VERSION.CODENAME);
        appendLog("Release : " + android.os.Build.VERSION.RELEASE);
    }

}
