package com.example.carrey.factory;

import com.example.carrey.enums.StrCodeToEnum;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


/**
 * 通用枚举转换器厂.
 */
public class StrCodeUniversalEnumConverterFactory
    implements ConverterFactory<String, StrCodeToEnum> {

  private static final Map<Class, Converter> converterMap = Maps.newConcurrentMap();

  @Override
  public <T extends StrCodeToEnum> Converter<String, T> getConverter(Class<T> targetType) {
    return Optional.ofNullable(converterMap.get(targetType))
        .orElseGet(() -> {
          Converter<String, T> result = new StrCodeToEnumConverter(targetType);
          converterMap.put(targetType, result);
          return result;
        });
  }

  static class StrCodeToEnumConverter<T extends StrCodeToEnum> implements Converter<String, T> {

    private Map<String, T> enumMap = Maps.newHashMap();

    private Class<T> enumType;

    public StrCodeToEnumConverter(Class<T> enumType) {
      this.enumType = enumType;
      initEnumMap();
    }

    private void initEnumMap() {
      T[] enums = enumType.getEnumConstants();
      Arrays.stream(enums)
          .filter(e -> StringUtils.isNotBlank(e.getStrCode()))
          .forEach(e -> enumMap.put(e.getStrCode(), e));
    }

    @Override
    public T convert(String s) {
      if (StringUtils.isBlank(s)) {
        throw new IllegalArgumentException("Illegal parameter");
      }
      return enumMap.get(s);
    }
  }
}
