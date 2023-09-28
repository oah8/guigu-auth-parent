package oah.project.system.annotation;

import oah.project.system.enums.BusinessType;
import oah.project.system.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @ClassName Log
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 20:16
 * @Version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;

}
