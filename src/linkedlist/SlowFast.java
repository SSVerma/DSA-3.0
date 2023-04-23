package linkedlist;

public class SlowFast {
    public static void main(String[] args) {
        LinkedList.Node first = new LinkedList.Node(1);
        LinkedList.Node second  = new LinkedList.Node(2);
        LinkedList.Node third = new LinkedList.Node(3);
        LinkedList.Node fourth = new LinkedList.Node(4);
        LinkedList.Node fifth = new LinkedList.Node(5);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
//        fifth.next = third;

        boolean result = hasCycle(first);
        System.out.println(result);
    }

    /*
        l1 => 1->2->3->4->5
        result => 3

        l2 => 1->2->3->4
        result => 2
     */
    public static LinkedList.Node findMid(LinkedList.Node head) {
        LinkedList.Node prev = null;
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast == null) {
            // even
            return prev;
        }

        // odd, fast.next is null
        return slow;
    }

    public static boolean hasCycle(LinkedList.Node head) {
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
