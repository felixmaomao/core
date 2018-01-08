package com.felix.core.log;


import com.felix.core.utils.JsonUtil;
import com.felix.core.utils.IpGetter;
import com.felix.core.utils.RedisManagerUtil;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shenwei
 * @Date 2017/12/28 12:08
 * @Description 自定义输出到redis 的LogAppender
*/
public class LogAppender extends AppenderSkeleton {
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
    private String appName;
    private String redisHost;
    private String redisPort;
    private String redisAuth;
    private String redisKey;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }

    public String getRedisAuth() {
        return redisAuth;
    }

    public void setRedisAuth(String redisAuth) {
        this.redisAuth = redisAuth;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    @Override
    protected void append(LoggingEvent event) {
        final LoggingEvent ev = event;
        final String appNames = this.appName;
        final String host = this.redisHost;
        final Integer port = Integer.parseInt(this.redisPort);
        final String auth = this.redisAuth;
        final String key = this.redisKey;

        fixedThreadPool.execute(new Runnable() {
            public void run() {
                LogCommand lc = new LogCommand();
                String ip = IpGetter.getIp();
                String hostName = IpGetter.getLocalHostName();
                lc.setIp(ip);
                lc.setAppName(appNames);
                StringBuffer sb = new StringBuffer();
                sb.append(ev.getRenderedMessage());
                lc.setContent(sb.toString());
                Timestamp ts = new Timestamp(ev.getTimeStamp());
                lc.setDtNow(ts);
                lc.setHostName(hostName);
                lc.setLogType(appNames);
                lc.setSite("");
                lc.setSiteID(0);
                //lc.setSubject(event.getLoggerName());
                lc.setUrl("http://");
                int levelLog = ev.getLevel().toInt();
                switch (levelLog) {
                    case 10000:
                        lc.setLogLevel(LogLevel.Debug);
                        break;
                    case 20000:
                        lc.setLogLevel(LogLevel.Info);
                        break;
                    case 30000:
                        lc.setLogLevel(LogLevel.Warn);
                        break;
                    case 40000:
                        lc.setLogLevel(LogLevel.Error);
                        break;
                    case 50000:
                        lc.setLogLevel(LogLevel.Fatal);
                        break;
                    default:
                        break;
                }
                RedisManagerUtil util = RedisManagerUtil.getInstance(host, port, auth);
                util.rpushNoLog(key, JsonUtil.toJSONString(lc));
            }
        });
    }

    public void close() {
        this.closed = true;
    }

    public boolean requiresLayout() {
        return false;
    }
}
