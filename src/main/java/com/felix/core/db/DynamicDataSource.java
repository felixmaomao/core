package com.felix.core.db;/**
 * Created by shenwei on 2018/1/8.
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author shenwei
 * @create 2018-01-08
 * 动态数据源切换
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * @Author shenwei
     * @Date 2018/1/8 14:27
     * @Description 数据源指定实现方法
     */
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }
}
