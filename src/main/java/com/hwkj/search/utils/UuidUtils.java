package com.hwkj.search.utils;

import java.util.UUID;

/**
 * UUID工具
 *
 * @author xzr
 * @date 2020/09/15 16:12
 */
public class UuidUtils {

    /**
     * 得到32位的uuid
     *
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
