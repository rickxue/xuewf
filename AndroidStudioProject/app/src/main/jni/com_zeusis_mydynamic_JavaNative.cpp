//
// Created by xuewf on 2016/7/22.
//

#include <jni.h>
#include <android/log.h>
#include "com_zeusis_mydynamic_JavaNative.h"
#define LOG_TAG "JavaNative"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT jint JNICALL Java_com_zeusis_mydynamic_JavaNative_nativeAdd
  (JNIEnv *, jobject, jint a, jint b){
  //LOGI("hello world! come from jni");
  return a + b;
}

JNIEXPORT jstring JNICALL Java_com_zeusis_mydynamic_JavaNative_nativeHelloWorld
        (JNIEnv *env, jobject obj){
  return env->NewStringUTF((char *)"Hello World! \nThis just a test for Android Studio NDK JNI developer!");

}