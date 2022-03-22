package 队列;

/**
 * @author Carrey
 * @className ArrayQueue
 * @description ArrayQueue
 * @date 2022/3/20 8:29 下午
 */
public class ArrayQueue<T> {

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(100);

        for (int i = 0; i < 100; i++) {
            arrayQueue.add(i + 1);
        }

        for (int i = 0; i < 50; i++) {
            int size = arrayQueue.size();
            Integer res = arrayQueue.poll();
            System.out.println("当前队列大小为：" + size + "输出结果：" + res);
        }

        for (int i = 50; i < 100; i++) {
            arrayQueue.add(i + 1);
        }
    }

    private T[] queue;

    private int head;

    private int tail;

    private int size;

    public int size() {
        return size;
    }

    public ArrayQueue(int capacity) {
        this.queue = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * 添加元素
     *
     * @param t
     * @return
     */
    public boolean add(T t) {
        if (null == t) {
            throw new NullPointerException();
        }
        if (queue.length == size) {
            throw new IndexOutOfBoundsException("Queue full");
        }
        queue[tail] = t;
        tail++;
        size++;
        return true;
    }


    /**
     * 获取队头元素，并删除队尾元素（如果队尾元素为空则返回null）
     *
     * @return
     */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T res = queue[head];
        queue[head] = null;
        head++;
        size--;
        //如果head的index已经大于queue的size的一半则要迁移数据
        if (head > (queue.length - 1) / 2) {
            for (int i = queue.length - 1; i >= queue.length - head; i--) {
                queue[i - head] = queue[i];
                queue[i] = null;
            }
            tail = queue.length - head - 1;
            head = 0;
        }
        return res;
    }

}
