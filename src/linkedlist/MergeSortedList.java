package linkedlist;

import java.util.PriorityQueue;

public class MergeSortedList {
    public static void main(String[] args) {
        MergeSortedList driver = new MergeSortedList();
        LinkedList.Node l1 = LinkedList.create(new int[]{1, 2, 5, 6, 8, 9});
        LinkedList.Node l2 = LinkedList.create(new int[]{3, 4, 7});
        LinkedList.Node l3 = LinkedList.create(new int[]{5, 8, 10});
        LinkedList.Node result = driver.mergeKSortedListApproach3(new LinkedList.Node[]{l1, l2, l3});
        LinkedList.display(result);
    }

    /*
        l1 => 1-> 2->5->6->8->9
        l2 => 3->4->7

        result => 1->2->3->4->5->6->7->8->9

     */
    public LinkedList.Node mergeTwoSortedList(LinkedList.Node l1, LinkedList.Node l2) {
        LinkedList.Node current = null;
        LinkedList.Node resultingHead = null;

        while (l1 != null && l2 != null) {
            LinkedList.Node node;
            if (l1.val <= l2.val) {
                node = l1;
                l1 = l1.next;
            } else {
                node = l2;
                l2 = l2.next;
            }

            if (current == null) {
                current = node;
                resultingHead = current;
            } else {
                current.next = node;
                current = current.next;
            }
        }

        if (l1 != null) {
            if (current == null) {
                // l2 is given null
                return l1;
            } else {
                current.next = l1;
            }
        }

        if (l2 != null) {
            if (current == null) {
                // l1 is given null
                return l2;
            } else {
                current.next = l2;
            }
        }

        return resultingHead;
    }

    // bit cleaner
    public LinkedList.Node mergeTwoSortedListWay2(LinkedList.Node l1, LinkedList.Node l2) {
        LinkedList.Node current = new LinkedList.Node(0);
        LinkedList.Node result = current;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return result.next;
    }

    public LinkedList.Node mergeTwoSortedListUsingRecursion(
            LinkedList.Node list1,
            LinkedList.Node list2
    ) {
        LinkedList.Node result = mergeTwoSortedListUsingRecursion(list1, list2, new LinkedList.Node(0));
        return result.next;
    }

    private LinkedList.Node mergeTwoSortedListUsingRecursion(
            LinkedList.Node list1,
            LinkedList.Node list2,
            LinkedList.Node result
    ) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            result.next = list1;
            mergeTwoSortedListUsingRecursion(list1.next, list2, result.next);
        } else {
            result.next = list2;
            mergeTwoSortedListUsingRecursion(list1, list2.next, result.next);
        }

        return result;
    }

    public LinkedList.Node mergeTwoSortedListUsingRecursionWay2(
            LinkedList.Node list1,
            LinkedList.Node list2
    ) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        LinkedList.Node result;

        if (list1.val <= list2.val) {
            result = list1;
            result.next = mergeTwoSortedListUsingRecursionWay2(list1.next, list2);
        } else {
            result = list2;
            result.next = mergeTwoSortedListUsingRecursionWay2(list1, list2.next);
        }

        return result;
    }

    // combine all, apply merge sort on combined list
    // TC: nlogn, n-> total node count
    public LinkedList.Node mergeKSortedList(LinkedList.Node[] lists) {
        LinkedList.Node head = CombineList.combine(lists);
        return mergeSortLinkedList(head);
    }

    private LinkedList.Node mergeSortLinkedList(LinkedList.Node head) {
        if (head.next == null) {
            return head;
        }

        LinkedList.Node mid = SlowFast.findMid(head);
        LinkedList.Node next = mid.next;
        // detach
        mid.next = null;

        mergeSortLinkedList(head); // first half
        mergeSortLinkedList(next); // second half

        return mergeTwoSortedList(head, next);
    }

    /*
        compare heads of all k lists, find the head having min value, move ahead
        the min head.

        l1 => 1->2->5->6->8->9
        l2 => 3->4->7
        l3 => 5->8->10

     */
    public LinkedList.Node mergeKSortedListApproach2(LinkedList.Node[] lists) {
        LinkedList.Node current = new LinkedList.Node(0);
        LinkedList.Node result = current;

        while (current != null) {
            Pair pair = findMinNodeWithIndex(lists);
            current.next = pair.node;
            current = current.next;
            if (pair.node != null) {
                // move ahead the min node pointer
                lists[pair.index] = pair.node.next;
            }
        }

        return result.next;
    }

    private Pair findMinNodeWithIndex(LinkedList.Node[] lists) {
        LinkedList.Node result = null;
        int index = 0;

        for (int i = 0; i < lists.length; i++) {
            LinkedList.Node node = lists[i];
            if (node == null) {
                continue;
            }
            if (result == null || node.val < result.val) {
                result = node;
                index = i;
            }
        }

        return new Pair(result, index);
    }

    public LinkedList.Node mergeKSortedListApproach3(LinkedList.Node[] lists) {
        LinkedList.Node current = new LinkedList.Node(0);
        LinkedList.Node result = current;

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.node.val - b.node.val);

        // populate min heap
        for (int i = 0; i < lists.length; i++) {
            minHeap.add(new Pair(lists[i], i));
        }

        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.remove();
            current.next = pair.node;
            current = current.next;
            if (pair.node.next != null) {
                minHeap.add(new Pair(pair.node.next, pair.index));
                // move ahead the min node pointer
                lists[pair.index] = pair.node.next;
            }
        }

        return result.next;
    }

    private static class Pair {
        LinkedList.Node node;
        int index;

        public Pair(LinkedList.Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
