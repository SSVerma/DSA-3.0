package linkedlist;

public class SlowFast {
    public static void main(String[] args) {
//        LinkedList.Node head = LinkedList.create(new int[]{1, 2, 3, 4, 5});
        LinkedList.Node head = LinkedList.create(new int[]{1, 2, 3, 4});
        LinkedList.Node result = findMid(head);
        System.out.println(result.val);
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
}
