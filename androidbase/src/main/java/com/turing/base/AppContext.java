package com.turing.base;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

/**
 * MyApp
 *
 * @author Mr.Yang on 2015/10/9  22:03.
 * @version 1.0
 * @desc
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 支持直接打印数据集合,如List、Set、Map、数组等
         全局配置log输出
         不需要设置tag
         准确显示调用方法、行，快速定位所在文件位置.
         */

        // 配置日志是否输出(默认true)
        LogUtils.configAllowLog = true;

        // 配置日志前缀
        LogUtils.configTagPrefix = "MrYang-";
    }
}
