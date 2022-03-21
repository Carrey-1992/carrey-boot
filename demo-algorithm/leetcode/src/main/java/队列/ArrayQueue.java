package 队列;

/**
 * @author Carrey
 * @className ArrayQueue
 * @description ArrayQueue
 * @date 2022/3/20 8:29 下午
 */
public class ArrayQueue<T> {

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(10000);

        for (int i = 0 ; i < 10000 ; i++) {
            arrayQueue.add(i + 1);
        }

        for (int i = 0 ; i < 10000 ; i++) {
            int size = arrayQueue.size();
            Integer res = arrayQueue.remove();
            System.out.println("当前队列大小为："+size+"输出结果："+ res);
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
        this.queue =  (T[]) new Object[capacity+1];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * 添加元素
     * @param t
     * @return
     */
    public boolean add(T t) {
        if (null == t) {
            throw new NullPointerException();
        }
        if (queue.length == tail + 1) {
            throw new IndexOutOfBoundsException("Queue full");
        }
        tail++;
        queue[tail] = t;
        size ++;
        return true;
    }

    /**
     * 将指定的元素插入此数据块的末尾
     * @param t
     * @return
     */
    public boolean offer(T t) {
        if (null == t) {
            throw new NullPointerException();
        }
        if (queue.length == tail + 1) {
            return false;
        }
        tail++;
        queue[tail] = t;
        return true;
    }

    /**
     * 删除队尾元素（如果队尾元素为空则抛出异常）
     * @return
     */
    public T remove() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Queue empty");
        }
        T res = queue[tail];
        queue[tail] = null;
        tail--;
        size --;
        return res;
    }

    /**
     * 获取队尾元素，并删除队尾元素（如果队尾元素为空则返回null）
     * @return
     */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T res = queue[tail];
        queue[tail] = null;
        tail--;
        size --;
        return res;
    }

    /**
     * 获取队尾元素，如果队尾元素不存在则抛出异常
     * @return
     */
    public T element() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Queue empty");
        }
        return queue[tail];
    }

    /**
     * 获取队尾元素，如果队尾元素不存在则返回null
     * @return
     */
    public T peek() {
        if (size == 0) {
            return null;
        }
        return queue[tail];
    }
}
