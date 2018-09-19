package com.chocfun.baselib.app;

import android.app.Activity;
import android.os.Process;

import com.chocfun.baselib.log.LogHelper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 管理Activity
 */
public class AppManager {
    private static List<Activity> mActivityList;

    private AppManager() {
        if (null == mActivityList) {
            mActivityList = new LinkedList<>();
        }
    }

    private static class SingletonHolder {
        private static AppManager instance = new AppManager();
    }

    public static AppManager getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 打印信息
     */
    public void printInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("共有 " + mActivityList.size() + " 个Activity\n");
        for (Activity activity : mActivityList) {
            builder.append(activity.getClass().toString());
            builder.append("\n");
        }
        LogHelper.i(builder.toString());
    }

    /**
     * 返回一个存储的{@link Activity} 的集合
     */
    public List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    /**
     * 添加{@link Activity}到集合
     *
     * @param activity Activity
     */
    public void addActivity(Activity activity) {
        synchronized (AppManager.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        }
    }

    /**
     * 删除集合中指定的{@link Activity}
     *
     * @param activity Activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            LogHelper.w("mActivityList == null when removeActivity()");
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 关闭指定{@link Activity} class 的所有实例
     *
     * @param activityClass Class of Activity
     */
    public void killActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            LogHelper.w("mActivityList == null when killActivity()");
            return;
        }
        synchronized (AppManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (next.getClass().equals(activityClass)) {
                    iterator.remove();
                    next.finish();
                }
            }
        }
    }

    /**
     * 检索指定{@link Activity} 实例是否存活
     *
     * @param activity Activity
     * @return 指定实例是否村存活
     */
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            LogHelper.w("mActivityList == null when activityInstanceIsLive()");
            return false;
        }
        return mActivityList.contains(activity);
    }

    /**
     * 获取指定{@link Activity} class 的实例, 没有则返回 null(同一个{@link Activity} class 可能存在多个实例， 返回最初创建的实例)
     *
     * @param activityClass Class of Activity
     * @return 指定Activity实例
     */
    public Activity findActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            LogHelper.w("mActivityList == null when findActivity()");
            return null;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 关闭所有{@link Activity}
     */
    public void killAll() {
        synchronized (AppManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 关闭除指定的{@link Activity} class 外的所有{@link Activity}
     *
     * @param excludeActivityClasses Class of Activity
     */
    public void killAll(Class<?>... excludeActivityClasses) {
        List<Class<?>> excludeList = Arrays.asList(excludeActivityClasses);
        synchronized (AppManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (excludeList.contains(next.getClass()))
                    continue;
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 关闭所有 {@link Activity},排除指定的 {@link Activity}
     *
     * @param excludeActivityName {@link Activity} 的完整全路径
     */
    public void killAll(String... excludeActivityName) {
        List<String> excludeList = Arrays.asList(excludeActivityName);
        synchronized (AppManager.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (excludeList.contains(next.getClass().getName()))
                    continue;

                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            killAll();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
