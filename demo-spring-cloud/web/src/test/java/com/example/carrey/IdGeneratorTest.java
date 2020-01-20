package com.example.carrey;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Random;

/**
 * @author Carrey
 * @version 0.0.1
 * @description IdGeneratorTest
 * @create 2020-01-20 10:38
 */
public class IdGeneratorTest {
  @Test
  public void testIdGenerator() {
    for (int i = 0;i < 4;i++) {
      IdGenerator idGenerator = new HostNameIdGenerator();
      System.out.println(idGenerator.generate());
    }
  }


  public class HostNameIdGenerator implements IdGenerator{
    private final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    @Override
    public String generate() {
      String id = "";
      try {
        //获取主机名
        final String hostName = getHostName();
        //获取八位随机字符串
        char[] randomChars = getEightBitRandomChars();
        //获取id
        id = String.format("%s-%d-%s", hostName, System.currentTimeMillis(), new String(randomChars));
      } catch (UnknownHostException e) {
        logger.warn("Failed to get the host name.", e);
      }

      return id;
    }

    private char[] getEightBitRandomChars() {
      char[] randomChars = new char[8];
      int count = 0;
      Random random = new Random();
      while (count < 8) {
        int randomAscii = random.nextInt(122);
        RandomCharsContext randomCharsContext = new RandomCharsContext();
        char newRandomChars = randomCharsContext.assignmentRandomChars(randomChars[count], randomAscii);
        if (newRandomChars != CharUtils.NUL) {
          randomChars[count] = newRandomChars;
          count++;
        }
      }
      return randomChars;
    }

    private String getHostName() throws UnknownHostException {
      String hostName = InetAddress.getLocalHost().getHostName();
      if (StringUtils.isEmpty(hostName)) {
        return StringUtils.EMPTY;
      }
      String[] tokens = hostName.split("\\.");
      if (tokens.length > 0) {
        hostName = tokens[tokens.length - 1];
      }
      return hostName;
    }
  }

  public interface IdGenerator {
    /**
     * 生产id
     * @return
     */
    String generate();
  }

  public class RandomCharsContext implements IRandomChars {

    private IRandomChars nextRandomChars;

    public RandomCharsContext() {
      LowercaseLetterRandomChars lowercaseLetterRandomChars = new LowercaseLetterRandomChars();

      UppercaseLetterRandomChars uppercaseLetterRandomChars = new UppercaseLetterRandomChars();
      uppercaseLetterRandomChars.setNextRandomChars(lowercaseLetterRandomChars);

      NumRandomChars numRandomChars = new NumRandomChars();
      numRandomChars.setNextRandomChars(uppercaseLetterRandomChars);

      nextRandomChars = numRandomChars;
    }

    @Override
    public char assignmentRandomChars(char randomChar, int randomAscii) {
      return nextRandomChars.assignmentRandomChars(randomChar,randomAscii);
    }
  }



  public class NumRandomChars extends AbstractRandomChars {

    private static final char DEFAULT_CHAR = '0';

    @Override
    protected char doAssignmentRandomChars(char randomChar, int randomAscii) {
      if (randomAscii >= 48 && randomAscii <= 57) {
        return (char)(DEFAULT_CHAR + (randomAscii - 48));
      }
      return CharUtils.NUL;
    }

  }



  public class UppercaseLetterRandomChars extends AbstractRandomChars {

    private static final char DEFAULT_CHAR = 'A';

    public char doAssignmentRandomChars(char randomChar, int randomAscii) {
      if (randomAscii >= 65 && randomAscii <= 90) {
        return (char)(DEFAULT_CHAR + (randomAscii - 65));
      }
      return CharUtils.NUL;
    }

  }



  public class LowercaseLetterRandomChars extends AbstractRandomChars {

    private static final char DEFAULT_CHAR = 'a';

    @Override
    protected char doAssignmentRandomChars(char randomChar, int randomAscii) {
      if (randomAscii >= 97 && randomAscii <= 122) {
        return (char)(DEFAULT_CHAR + (randomAscii - 97));
      }
      return CharUtils.NUL;
    }
  }

  public abstract class AbstractRandomChars implements IRandomChars{
    private IRandomChars nextRandomChars;

    public void setNextRandomChars(IRandomChars nextRandomChars) {
      this.nextRandomChars = nextRandomChars;
    }

    public char assignmentRandomChars(char randomChar, int randomAscii) {
      char newRandomChar = doAssignmentRandomChars(randomChar, randomAscii);
      if (newRandomChar!= CharUtils.NUL) {
        return newRandomChar;
      }

      if (Objects.isNull(nextRandomChars)) {
        return CharUtils.NUL;
      }

      return nextRandomChars.assignmentRandomChars(randomChar,randomAscii);

    }

    /**
     * 组装随机字符
     * @param randomChar
     * @param randomAscii
     * @return
     */
    protected abstract char doAssignmentRandomChars(char randomChar, int randomAscii);
  }

  public interface IRandomChars {
    /**
     * 为随机字符串赋值
     * @param randomChar
     * @param randomAscii
     * @return
     */
    char assignmentRandomChars(char randomChar, int randomAscii);
  }
}
