package linkedlist;

public class LinkedList {
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class Builder {
        private Node head = null;
        private Node current = null;

        public Builder append(int val) {
            if (current == null) {
                current = new Node(val);
                head = current;
            } else {
                current.next = new Node(val);
                current = current.next;
            }
            return this;
        }

        public Node build() {
            return head;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /*
        LinkedList.create(new int[]{1, 5, 3, 6, 2, 7});
        result => 1->5->3->6->2->7
     */
    public static Node create(int[] elements) {
        Builder builder = LinkedList.builder();

        for (int element : elements) {
            builder.append(element);
        }

        return builder.build();
    }

    /*
        Handy method to create LinkedList quickly.

        LinkedList.create(5);
        result => 1->2->3->4->5
    */
    public static Node create(int nodeCount) {
        Builder builder = LinkedList.builder();

        for (int element = 1; element <= nodeCount; element++) {
            builder.append(element);
        }

        return builder.build();
    }

    public static void display(Node head) {
        StringBuilder builder = new StringBuilder();

        while (head != null) {
            builder.append(head.val);
            if (head.next != null) {
                builder.append("->");
            }
            head = head.next;
        }

        System.out.println(builder);
    }
}
