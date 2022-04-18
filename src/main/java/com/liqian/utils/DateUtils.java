package com.liqian.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/*
* 时间工具类
* */
public class DateUtils {

    public static LocalDateTime getDateTime(Long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant,zoneId);
    }

}
