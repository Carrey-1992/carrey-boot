package com.example.carrey.factory;

import com.example.carrey.enums.IntCodeToEnum;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public class IntCodeUniversalEnumConverterFactory
    implements ConverterFactory<String, IntCodeToEnum> {

  private static final Map<Class, Converter> converterMap = Maps.newConcurrentMap();

  @Override
  public <T extends IntCodeToEnum> Converter<String, T> getConverter(Class<T> targetType) {
    return Optional.ofNullable(converterMap.get(targetType))
        .orElseGet(() -> {
          Converter<String, T> result = new IntCodeToEnumConverter(targetType);
          converterMap.put(targetType, result);
          return result;
        });
  }


  static class IntCodeToEnumConverter<T extends IntCodeToEnum> implements Converter<String, T> {

    private Map<String, T> enumMap = Maps.newHashMap();

    private Class<T> enumType;

    public IntCodeToEnumConverter(Class<T> enumType) {
      this.enumType = enumType;
      initEnumMap();
    }

    private void initEnumMap() {
      T[] enums = enumType.getEnumConstants();
      Arrays.stream(enums)
          .filter(e -> Objects.nonNull(e.getIntCode()))
          .forEach(e -> enumMap.put(String.valueOf(e.getIntCode()), e));
    }

    @Override
    public T convert(String s) {
      if (StringUtils.isBlank(s)) {
        throw new IllegalArgumentException("No element matches ");
      }
      return enumMap.get(s);
    }
  }
}
