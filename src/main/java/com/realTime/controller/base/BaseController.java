package com.realTime.controller.base;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/2/26 14:24
 */
public interface BaseController {


    /**
    *   默认项目启动的时候执行一下
    * */
    public void init();


    /**
     *   默认项目关闭的时候执行一下
     * */
    public void close();
}
