package com.chocfun.baselib.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class XLog implements BaseLogStrategy {
    private @LogConstants.LogLevel
    int mLevel = LogConstants.DEBUG;
    private String mOriginalTAG;
    private String mClassName;
    private String mMethodName;
    private int mLineNumber;
    private String mWrapperName;
    private String mTAG = XLog.class.getSimpleName().toString();

    @Override
    public void init(String tag) {
        this.mOriginalTAG = tag;
        resetDefaultLevel();
    }

    @Override
    public void setLevel(@LogConstants.LogLevel int level) {
        mLevel = level;
    }

    @Override
    public void setTag(String tag) {
        mTAG = tag;
    }

    @Override
    public void setWrapper(String wrapper) {
        mWrapperName = wrapper;
    }

    @Override
    public void print(@NonNull String message, @Nullable Object... args) {
        String methodLog = getMethodLog();

        print(methodLog, String.format(message, args));
    }

    @Override
    public void print(@NonNull Object object) {
        String methodLog = getMethodLog();

        print(methodLog, LogConstants.toString(object));
    }

    private void print(String methodLog, String message) {
        switch (mLevel) {
            case LogConstants.VERBOSE:
                Log.v(getTAG(), methodLog);
                Log.v(getTAG(), message);
                break;
            case LogConstants.DEBUG:
                Log.d(getTAG(), methodLog);
                Log.d(getTAG(), message);
                break;
            case LogConstants.INFO:
                Log.i(getTAG(), methodLog);
                Log.i(getTAG(), message);
                break;
            case LogConstants.WARN:
                Log.w(getTAG(), methodLog);
                Log.w(getTAG(), message);
                break;
            case LogConstants.ERROR:
                Log.e(getTAG(), methodLog);
                Log.e(getTAG(), message);
                break;
        }

        // 重置日志等级
        resetDefaultLevel();
    }

    /**
     * 重置日志等级
     */
    private void resetDefaultLevel() {
        setLevel(LogConstants.DEBUG);
        mTAG = mOriginalTAG;
    }

    private String getTAG() {
        return TextUtils.isEmpty(mTAG) ? XLog.class.getSimpleName() : mTAG;
    }

    private String getMethodLog() {
        getMethodInfo(getTargetStackTraceElement());

        return "(" + mClassName + ":" + mLineNumber + ")";
    }


    private void getMethodInfo(StackTraceElement sElements) {
        mClassName = sElements.getFileName();
        mMethodName = sElements.getMethodName();
        mLineNumber = sElements.getLineNumber();
    }

    private StackTraceElement getTargetStackTraceElement() {
        // find the target invoked method
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement
                    .getClassName()
                    .equals(TextUtils.isEmpty(mWrapperName) ? LogHelper.class.getName() : mWrapperName);
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

}
