package 知名算法;

import com.sun.tools.javac.util.Assert;

/**
 * @author Carrey
 * @className 约瑟夫问题
 * @description 约瑟夫问题
 * @date 2022/1/27 8:25 下午
 */
public class 约瑟夫问题 {

    /**
     * 留下的就是1经典算法-约瑟夫环问题（初级版）
     * 直接上经典的例题：
     * 案例1:
     * 已知n个人（以编号1，2，3…n分别表示）围坐在一张圆桌周围。从编号为1的人开始报数，数到m的那个人出列；
     * 他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人全部出列（也类似于变态杀人狂问题）。
     * <p>
     * 例如N=6,M=5,被杀掉的顺序是：5，4，6，2，3，1
     * 现在问你最后留下的人是谁？
     * <p>
     * 常见的解法有三种：数组，链表和递归。
     * 单循环链表求解
     * 123456=》61234=》6123=》123=》=13=》1
     * 123456=》56123=》3561=》356=》=》56 =》5
     *
     * @param args
     */
    public static void main(String[] args) {
        int result = joseph(6, 4);
        System.out.println("约瑟夫结果为：" + result);
    }


    public static int joseph(int n, int m) {
        System.out.println("约瑟夫的入参n为：" + n + "，m为：" + m );
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        for (int i = 1; i <= n; i++) {
            linkedList.addTail(i);
        }
        System.out.println("当前内容为："+linkedList);
        return linkedList.joseph(m);
    }

    public static class CircularLinkedList<T> {

        /**
         * 头
         */
        private Node<T> head;

        /**
         * 尾
         */
        private Node<T> tail;

        private int size;

        public void addHead(T value) {
            Assert.checkNonNull(value);
            Node<T> node = new Node<>(value, head);
            head = node;
            if (size == 0) {
                tail = node;
            } else {
                tail.next = head;
            }
            size++;
        }

        public void addTail(T value) {
            Assert.checkNonNull(value);
            if (size == 0) {
                addHead(value);
            } else {
                Node<T> node = new Node<>(value, head);
                tail.next = node;
                tail = node;
                size++;
            }
        }

        public void remove(int index) {
            Assert.check(index >= 0 && index < size);
            if (index == 0) {
                head = head.next;
                tail.next = head;
            } else {
                Node<T> node = head;
                for (int i = 1; i <= size; i++) {
                    if (i == index) {
                        node.next = node.next.next;
                        break;
                    }
                    node = node.next;
                }
            }
            size--;
        }

        public T get(int index) {
            Node<T> node = node(index);
            return node == null ? null : node.value;
        }

        private Node<T> node(int index) {
            Assert.check(index >= 0);
            if (index == 0) {
                return head;
            }
            if (index == size - 1) {
                return tail;
            }
            Node<T> node = head;
            for (int i = 1; i <= index; i++) {
                Node<T> next = node.next;
                if (i == index) {
                    return next;
                }
                node = next;
            }
            return null;
        }

        public int getSize() {
            return size;
        }

        public T joseph(int m) {
            int index = m - 1;
            while (size > 1) {
                josephRemove(index);
                System.out.println("删除之后为："+toString());
            }
            return get(0);
        }

        private void josephRemove(int index) {
            Assert.check(index >= 0);
            if (index > size - 1) {
                index = index - size;
            }
            if (index == 0) {
                head = head.next;
                tail.next = head;
            } else {
                Node<T> node = head;
                for (int i = 1; i <= size; i++) {
                    if (i == index) {
                        node.next = node.next.next;
                        head = node.next;
                        tail = node;
                        break;
                    }
                    node = node.next;
                }
            }
            size--;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(get(i));
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }


        public static class Node<T> {

            private Node next;

            private T value;

            public Node(T value, Node next) {
                this.next = next;
                this.value = value;
            }
        }
    }


}
