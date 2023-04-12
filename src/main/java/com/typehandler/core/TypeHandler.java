package com.typehandler.core;

import com.typehandler.core.domain.TypeConversionEntity;

/**
 * 类型处理器接口
 *
 * @param <T>
 * @param <R>
 */
public interface TypeHandler<T, R> {
    /**
     * 原类型转换为目标类型
     *
     * @param parameter
     * @return
     */
    R typeConversion(T parameter);

    /**
     * 复杂类型转换
     * @param parameter
     * @return
     */
    R typeConversion(TypeConversionEntity<T> parameter);
}
