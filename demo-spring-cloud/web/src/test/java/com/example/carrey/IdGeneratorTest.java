package com.example.carrey;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
        boolean flag = randomCharsContext.getRandomChars(randomChars, count, randomAscii);
        if (flag) {
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

  public class RandomCharsContext {
    public boolean getRandomChars(char[] randomChars, int count,int randomAscii) {
      IRandomChars iRandomChars = new NumRandomChars();
      return iRandomChars.getRandomChars(randomChars,count,randomAscii);
    }
  }

  public class NumRandomChars implements IRandomChars {

    private static final char DEFAULT_CHAR = '0';

    private IRandomChars nextRandomChars = new UppercaseLetterRandomChars();

    public boolean getRandomChars(char[] randomChars, int count,int randomAscii) {
      if (randomAscii >= 48 && randomAscii <= 57) {
        randomChars[count] = (char)(DEFAULT_CHAR + (randomAscii - 48));
        return true;
      }
      return nextRandomChars.getRandomChars(randomChars,count,randomAscii);
    }
  }

  public class UppercaseLetterRandomChars implements IRandomChars {

    private static final char DEFAULT_CHAR = 'A';

    private IRandomChars nextRandomChars = new LowercaseLetterRandomChars();

    public boolean getRandomChars(char[] randomChars, int count,int randomAscii) {
      if (randomAscii >= 65 && randomAscii <= 90) {
        randomChars[count] = (char)(DEFAULT_CHAR + (randomAscii - 65));
        count++;
        return true;
      }
      return nextRandomChars.getRandomChars(randomChars,count,randomAscii);
    }
  }

  public class LowercaseLetterRandomChars implements IRandomChars {

    private static final char DEFAULT_CHAR = 'a';

    public boolean getRandomChars(char[] randomChars, int count,int randomAscii) {
      if (randomAscii >= 97 && randomAscii <= 122) {
        randomChars[count] = (char)(DEFAULT_CHAR + (randomAscii - 97));
        count++;
        return true;
      }
      return false;
    }
  }

  public interface IRandomChars {
    /**
     * 获取随机字符
     * @param randomChars
     * @param count
     * @param randomAscii
     * @return
     */
    boolean getRandomChars(char[] randomChars, int count,int randomAscii);
  }
}
