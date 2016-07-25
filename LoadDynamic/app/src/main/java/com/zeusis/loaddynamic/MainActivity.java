package com.zeusis.loaddynamic;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zeusis.mydynamic.UserInterfaces;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private String mJarName = "mytest.jar";
    private String mSoName = "libmydynamic.so";
    private String mClassName = "com.zeusis.mydynamic.JavaNative";
    private Button mButton;
    private TextView mTextView;
    private TextView mHelloWorld;
    private String mPath = "/vendor/lib/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloWorld = (TextView) findViewById(R.id.textView);
        mTextView = (TextView) findViewById(R.id.textView2);
        mButton = (Button) findViewById(R.id.button);

        CopyFileToLocal(Environment.getExternalStoragePublicDirectory("Download").getAbsolutePath()
                        + File.separator + mJarName,
                mJarName);
        CopyFileToLocal(Environment.getExternalStoragePublicDirectory("Download").getAbsolutePath()
                        + File.separator + mSoName,
                mSoName);
        LoadJar();
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
    private void LoadJar(){
/*        File dexDir = new File(this.getFilesDir().getAbsolutePath());
        File jarFile = new File(this.getFilesDir().getAbsolutePath()
                +File.separator + mJarName);
        File soFile = new File(this.getFilesDir().getAbsolutePath()
                + File.separator + mSoName);*/

        DexClassLoader cl = new DexClassLoader(getFilesDir().getAbsolutePath()
                + File.separator + mJarName,
                getFilesDir().getAbsolutePath(), getFilesDir().getAbsolutePath(), getClassLoader());

        Class libProviderClazz = null;
        try {
            libProviderClazz = cl.loadClass(mClassName);
            UserInterfaces customInterface = (UserInterfaces)libProviderClazz.newInstance();
            mTextView.setText(String.valueOf(customInterface.javaAdd(5,10)));
            mHelloWorld.setText(customInterface.HelloWorld());

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void CopyFileToLocal(String input, String output){

        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(input);
            if(oldfile.exists()){
                FileInputStream inStream = new FileInputStream(input);
                FileOutputStream fs = openFileOutput(output,MODE_PRIVATE);
                byte[] buffer = new byte[1444];
                while( (byteread = inStream.read(buffer)) != -1){
                    bytesum += byteread;
                    System.out.print(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }

            String permission="600";
            String filePath = getFilesDir().getAbsolutePath()+File.separator+output;
            try {
                String command = "chmod " + permission + " " + filePath;
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(command);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e){
            System.out.print("复制单个文件操作出错");
            e.printStackTrace();
        }

    }

    private void CopyFile(String input, String output){

        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(input);
            File sofile = new File(mPath,output);
            sofile.createNewFile();
            if(oldfile.exists()){
                FileInputStream inStream = new FileInputStream(input);
                FileOutputStream fs = new FileOutputStream(sofile.getName());
                byte[] buffer = new byte[1444];
                while( (byteread = inStream.read(buffer)) != -1){
                    bytesum += byteread;
                    System.out.print(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }

            String permission="666";
            String filePath = getFilesDir().getAbsolutePath()+File.separator+output;
            try {
                String command = "chmod " + permission + " " + filePath;
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(command);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e){
            System.out.print("复制单个文件操作出错");
            e.printStackTrace();
        }

    }
}

