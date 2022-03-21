package com.carrey.carrey.utils;

import com.carrey.carrey.enums.BaseByteCodeEnum;
import com.carrey.carrey.enums.BaseIntegerCodeEnum;
import com.carrey.carrey.enums.BaseStringCodeEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Carrey
 * @className EnumUtils
 * @description EnumUtils
 * @date 2021/9/16 4:41 下午
 */
@UtilityClass
public class EnumUtils {
    /**
     * 根据int类型code获取相应枚举
     * <pre>
     *     DemoEnum demoEnum = EnumUtils.getEnumsByIntCode(DemoEnum.class,intCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param intCode Integer类型的编码
     * @param <E> 实现BaseIntegerCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseIntegerCodeEnum> E getEnumByIntCode(Class<E> eClass, Integer intCode) {
        if (Objects.isNull(intCode)) {
            return null;
        }
        BaseIntegerCodeEnum[] enumConstants = eClass.getEnumConstants();
        if (ArrayUtils.isEmpty(enumConstants)) {
            return null;
        }
        for (BaseIntegerCodeEnum e : enumConstants) {
            if (Objects.equals(e.getIntCode(),intCode)) {
                return (E)e;
            }
        }
        return null;
    }

    /**
     * 根据string类型code获取相应枚举
     * <pre>
     *     DemoEnum demoEnum = EnumUtils.getEnumsByStrCode(DemoEnum.class,strCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param strCode String类型的编码
     * @param <E> 实现BaseStringCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseStringCodeEnum> E getEnumByStrCode(Class<E> eClass, String strCode) {
        if (StringUtils.isBlank(strCode)) {
            return null;
        }
        BaseStringCodeEnum[] enumConstants = eClass.getEnumConstants();
        if (ArrayUtils.isEmpty(enumConstants)) {
            return null;
        }
        for (BaseStringCodeEnum e : enumConstants) {
            if (Objects.equals(e.getStrCode(),strCode)) {
                return (E)e;
            }
        }
        return null;
    }

    /**
     * 根据Byte类型code获取相应枚举
     * <pre>
     *     DemoEnum demoEnum = EnumUtils.getEnumsByByteCode(DemoEnum.class,byteCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param byteCode Byte类型的编码
     * @param <E> 实现BaseByteCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseByteCodeEnum> E getEnumByByteCode(Class<E> eClass, Byte byteCode) {
        if (Objects.isNull(byteCode)) {
            return null;
        }
        BaseByteCodeEnum[] enumConstants = eClass.getEnumConstants();
        if (ArrayUtils.isEmpty(enumConstants)) {
            return null;
        }
        for (BaseByteCodeEnum e : enumConstants) {
            if (Objects.equals(e.getByteCode(),byteCode)) {
                return (E)e;
            }
        }
        return null;
    }

    /**
     * 根据int类型code获取相应枚举的Optional类型对象
     * <pre>
     *     Optional<DemoEnum> demoEnumOpt = EnumUtils.getEnumsByIntCode(DemoEnum.class,intCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param intCode Integer类型的编码
     * @param <E> 实现BaseIntegerCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseIntegerCodeEnum> Optional<E> getEnumOptByIntCode(Class<E> eClass, Integer intCode) {
        return Optional.ofNullable(getEnumByIntCode(eClass,intCode));
    }

    /**
     * 根据string类型code获取相应枚举的Optional类型对象
     * <pre>
     *     Optional<DemoEnum> demoEnumOpt = EnumUtils.getEnumsByStrCode(DemoEnum.class,strCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param strCode String类型的编码
     * @param <E> 实现BaseStringCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseStringCodeEnum> Optional<E> getEnumOptByStrCode(Class<E> eClass, String strCode) {
        return Optional.ofNullable(getEnumByStrCode(eClass,strCode));
    }

    /**
     * 根据Byte类型code获取相应枚举的Optional类型对象
     * <pre>
     *     Optional<DemoEnum> demoEnumOpt = EnumUtils.getEnumsByByteCode(DemoEnum.class,byteCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param byteCode Byte类型的编码
     * @param <E> 实现BaseByteCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseByteCodeEnum> Optional<E> getEnumOptByByteCode(Class<E> eClass, Byte byteCode) {
        return Optional.ofNullable(getEnumByByteCode(eClass,byteCode));
    }

    /**
     * 根据int类型code获取相应枚举的描述信息
     * <pre>
     *     String desc = EnumUtils.getEnumDescByIntCode(DemoEnum.class,intCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param intCode Integer类型的编码
     * @param <E> 实现BaseIntegerCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseIntegerCodeEnum> String getEnumDescByIntCode(Class<E> eClass, Integer intCode) {
        return getEnumOptByIntCode(eClass,intCode).map(E::getDesc).orElse(StringUtils.EMPTY);
    }

    /**
     * 根据string类型code获取相应枚举的描述信息
     * <pre>
     *     String desc = EnumUtils.getEnumDescByStrCode(DemoEnum.class,strCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param strCode String类型的编码
     * @param <E> 实现BaseStringCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseStringCodeEnum> String getEnumDescByStrCode(Class<E> eClass, String strCode) {
        return getEnumOptByStrCode(eClass,strCode).map(E::getDesc).orElse(StringUtils.EMPTY);
    }

    /**
     * 根据Byte类型code获取相应枚举的描述信息
     * <pre>
     *     String desc = EnumUtils.getEnumDescByByteCode(DemoEnum.class,byteCode);
     * </pre>
     * @param eClass 枚举类型Class
     * @param byteCode Byte类型的编码
     * @param <E> 实现BaseByteCodeEnum接口的枚举类型Type
     * @return
     */
    public <E extends BaseByteCodeEnum> String getEnumDescByByteCode(Class<E> eClass, Byte byteCode) {
        return getEnumOptByByteCode(eClass,byteCode).map(E::getDesc).orElse(StringUtils.EMPTY);
    }
}
