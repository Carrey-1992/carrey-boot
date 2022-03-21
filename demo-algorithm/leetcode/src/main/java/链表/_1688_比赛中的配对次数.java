package 链表;

/**
 * @author Carrey
 * @className _1688_比赛中的配对次数
 * @description _1688_比赛中的配对次数
 * @date 2022/1/25 10:09 下午
 */
public class _1688_比赛中的配对次数 {

    public static void main(String[] args) {
        int result = numberOfMatches(14);
        System.out.println("结果为：" + result);
    }

    public static int numberOfMatches(int n) {
        int result = 0;
        while (n > 1) {
            if ((n & (n-1)) > 0) {
                result += (n-1)/2;
                n = (n-1)/2 + 1;
            } else {
                result += n/2;
                n = n/2;
            }
        }
        return result;
    }
}
