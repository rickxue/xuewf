LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := mydynamic
LOCAL_C_INCLUDES := $(JNI_H_INCLUDE)
LOCAL_SRC_FILES := com_zeusis_mydynamic_JavaNative.cpp

LOCAL_PRELINK_MODULE := false

LOCAL_LDLIBS += -llog
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog

LOCAL_SHARED_LIBRARIES := libutils

include $(BUILD_SHARED_LIBRARY)
