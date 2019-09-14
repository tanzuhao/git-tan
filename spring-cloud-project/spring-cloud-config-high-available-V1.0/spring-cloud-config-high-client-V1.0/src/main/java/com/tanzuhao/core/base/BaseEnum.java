package com.tanzuhao.core.base;
/**
 * 基础枚举接口
 * @author tanzuhao
 * @date: 2019年9月9日 下午5:23:18  
 * @param <K>
 * @param <V>
 */
public interface BaseEnum<K, V> {

    /**
     * 获取编码
     *
     * @return 编码
     */
    K code();

    /**
     * 获取描述
     * 
     * @return 描述
     */
    V desc();

}