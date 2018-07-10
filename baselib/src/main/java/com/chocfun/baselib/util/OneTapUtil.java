package com.chocfun.baselib.util;

/**
 * 检测快速点击
 */
public class OneTapUtil {
    // 两次点击的间隔时间
    private static final int CLICK_DELAY = 1000;
    // 记录 view id
    private static int mClickId;
    // 上次点击时间
    private static long mLastMills;


    /**
     * 粗略检测防抖，只能对单个view起效
     * @param id 点击view的id
     * @return 是否不能点击，true 不能点击 false 可点击
     */
    public static boolean checkInexact(int id) {
        if (id == mClickId) {
            if (System.currentTimeMillis() - mLastMills > CLICK_DELAY) {
                mLastMills = System.currentTimeMillis();
                return false;
            } else {
                return true;
            }
        } else {
            mLastMills = System.currentTimeMillis();
            mClickId = id;
            return false;
        }
    }

}
