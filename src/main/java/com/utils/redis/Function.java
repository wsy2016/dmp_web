package com.utils.redis;

/**
 * Description:
 * <p>
 * Author: wsy
 * <p>
 * Date: 2019/1/24 17:48
 */
public interface Function<T, E> {

    /**
     *
     * Description:回到方法E获取T
     * Author: wsy
     * Date: 2019/1/24 17:49
     * Param: [e]
     * Return: T
     */
    public T callback(E e);


}
