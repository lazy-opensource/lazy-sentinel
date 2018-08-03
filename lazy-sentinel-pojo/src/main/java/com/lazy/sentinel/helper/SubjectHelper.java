package com.lazy.sentinel.helper;

import org.apache.shiro.SecurityUtils;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/4/1.
 */
public class SubjectHelper {

    /**
     * 获取当前主体的name
     *
     * @return
     */
    public static String getSubjectName() {
        Object subjectName = SecurityUtils.getSubject().getPrincipal();
        if (subjectName == null){
            //TODO 超时
            SecurityUtils.getSubject().logout();
            return "";
        }
        return subjectName.toString();
    }
}
