package com.zeusis.mydynamic;

/**
 * Created by xuewf on 2016/7/22.
 */
public class JavaNative implements UserInterfaces{
    static {
    System.loadLibrary("mydynamic");}
    @Override
    public int javaAdd(int a, int b){
        return nativeAdd(a, b);
    }
    @Override
    public String HelloWorld(){
        return nativeHelloWorld();
    }

    public native int nativeAdd(int a, int b);
    public native String nativeHelloWorld();
}
