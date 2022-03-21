package 数组;

/**
 * @author Carrey
 * @className _121_买卖股票的最佳时机
 * @description _121_买卖股票的最佳时机
 * @date 2022/1/20 7:03 下午
 */
public class _121_买卖股票的最佳时机 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println();
        int[] prices = new int[]{10000,9999,9998};
        int res = maxProfit(prices);
        System.out.println("_121_买卖股票的最佳时机的参数为："+prices.toString()
                +"，结果为："+res+"用时为："+(System.currentTimeMillis() - startTime) + "毫秒");
    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int a = prices[i];
            result = Math.max(result,a - min);
            min = Math.min(min,a);
        }
        return result;
    }

}
