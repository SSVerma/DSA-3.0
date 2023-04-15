package linkedlist;

import java.util.Stack;

public class Reverse {
    public static void main(String[] args) {
        Reverse reverse = new Reverse();
//        LinkedList.Node head = LinkedList.create(new int[]{1, 4, 5, 3, 7});
        LinkedList.Node head = LinkedList.create(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        LinkedList.Node result = reverse.reverseInKGroupType2UsingRecursion(head, 3);
        LinkedList.display(result);
    }

    // TC: O(n), SC: O(n)
    public LinkedList.Node reverseApproachOne(LinkedList.Node head) {
        Stack<LinkedList.Node> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        LinkedList.Node current = null;
        LinkedList.Node reverseHead = null;

        while (!stack.isEmpty()) {
            if (current == null) {
                current = stack.pop();
                reverseHead = current;
            } else {
                current.next = stack.pop();
                current = current.next;
            }
        }

        current.next = null;

        return reverseHead;
    }

    // TC: O(n), SC: O(1)
    public LinkedList.Node reverseApproachTwo(LinkedList.Node head) {
        LinkedList.Node current = head;
        LinkedList.Node prev = null;

        while (current != null) {
            LinkedList.Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // TC: O(n), SC: O(n) => recursion implicit stack
    public LinkedList.Node reverseUsingRecursion(LinkedList.Node head) {
        return reverse(head, null);
    }

    private LinkedList.Node reverse(LinkedList.Node head, LinkedList.Node prev) {
        if (head == null) {
            return prev;
        }

        LinkedList.Node next = head.next;
        head.next = prev;

        return reverse(next, head);
    }

    /*
        1->2->3->4->5->6->7->8
        k = 3

        result: 3->2->1->6->5->4->8->7

        -------------------------------

        1->2->3
        ------- on chunk of k size
        ^     ^
        1: chunkStart

        after chunk reversal
        3->2->1 => 6->5->4
                   ------- current chunk

        1: lastChunkEnd

     */
    private LinkedList.Node reverseInKGroup(LinkedList.Node head, int k) {
        LinkedList.Node lastChunkEnd = new LinkedList.Node(0);
        LinkedList.Node reverseHead = lastChunkEnd;

        while (head != null) {
            int count = 0;
            LinkedList.Node prev = null;
            LinkedList.Node chunkStart = head;

            while (head != null && count++ < k) {
                LinkedList.Node next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }

            // prev is head of reversed linked list (reversed chunk)
            // if 1->2->3 is chunk, then after reverse 3->2->1, 3 is pointed by prev or head of reversed chunk
            lastChunkEnd.next = prev;
            lastChunkEnd = chunkStart;
        }

        return reverseHead.next;
    }

    private LinkedList.Node reverseInKGroupUsingRecursion(LinkedList.Node head, int k) {
        if (head == null) {
            return null;
        }

        int count = 0;
        LinkedList.Node prev = null;
        LinkedList.Node current = head;

        while (current != null && count++ < k) {
            LinkedList.Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head.next = reverseInKGroupUsingRecursion(current, k);
        return prev;
    }

    /*
    1->2->3->4->5->6->7->8
    k = 3

    result: 3->2->1->6->5->4->7->8

    Constraint: If last chunk size is less than k, it should not be reversed.

 */
    public LinkedList.Node reverseInKGroupType2(LinkedList.Node head, int k) {
        int size = linkedListSize(head);
        int totalChunks = size / k + 1;
        boolean isLastChunkIncomplete = size % k != 0;

        LinkedList.Node lastChunkEnd = new LinkedList.Node(0);
        LinkedList.Node reverseHead = lastChunkEnd;

        int currentChunk = 1;

        while (head != null) {
            int count = 0;
            LinkedList.Node prev = null;
            LinkedList.Node chunkStart = head;

            if (currentChunk == totalChunks && isLastChunkIncomplete) {
                // last chunk and that also incomplete
                // in this case attach last chunk without reversing
                lastChunkEnd.next = head;
                return reverseHead.next;
            }

            while (head != null && count++ < k) {
                LinkedList.Node next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }

            currentChunk++;

            // prev is head of reversed linked list (reversed chunk)
            // if 1->2->3 is chunk, then after reverse 3->2->1, 3 is pointed by prev or head of reversed chunk
            lastChunkEnd.next = prev;
            lastChunkEnd = chunkStart;
        }

        return reverseHead.next;
    }

    private int linkedListSize(LinkedList.Node head) {
        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    public LinkedList.Node reverseInKGroupType2UsingRecursion(LinkedList.Node head, int k) {
        int size = linkedListSize(head);
        int totalChunks = size / k + 1;
        boolean isLastChunkIncomplete = size % k != 0;

        return reverseInKGroupType2UsingRecursion(
                head,
                k,
                1,
                totalChunks,
                isLastChunkIncomplete
        );
    }

    private LinkedList.Node reverseInKGroupType2UsingRecursion(
            LinkedList.Node head,
            int k,
            int currentChunk,
            int totalChunks,
            boolean isLastChunkIncomplete
    ) {
        if (head == null) {
            return null;
        }

        if (isLastChunkIncomplete && currentChunk == totalChunks) {
            return head;
        }

        int count = 0;
        LinkedList.Node prev = null;
        LinkedList.Node current = head;

        while (current != null && count++ < k) {
            LinkedList.Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head.next = reverseInKGroupType2UsingRecursion(
                current,
                k,
                currentChunk + 1,
                totalChunks,
                isLastChunkIncomplete
        );
        return prev;
    }
}
