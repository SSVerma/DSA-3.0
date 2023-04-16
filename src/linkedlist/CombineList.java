package linkedlist;

public class CombineList {
    public static void main(String[] args) {
        LinkedList.Node l1 = LinkedList.create(new int[]{1, 2, 9});
        LinkedList.Node l2 = LinkedList.create(new int[]{4, 3, 8});
        LinkedList.Node l3 = LinkedList.create(new int[]{2, 7, 5, 6});

        LinkedList.Node result = combineRecursive(new LinkedList.Node[]{l1, l2, l3});
        LinkedList.display(result);
    }

    /*
        l1 = 1->2->9
        l2 = 4->3->8
        l3 = 2->7->5->6

        result => 1->2->9->4->3->8->2->7->5->6
    */
    public static LinkedList.Node combine(LinkedList.Node[] lists) {
        LinkedList.Node last = new LinkedList.Node(0);
        LinkedList.Node result = last;

        for (int i = 0; i < lists.length; i++) {
            last.next = lists[i];
            last = findLastNode(lists[i]);
        }

        return result.next;
    }

    public static LinkedList.Node combineRecursive(LinkedList.Node[] lists) {
        return combineRecursive(lists, 0);
    }

    private static LinkedList.Node combineRecursive(
            LinkedList.Node[] lists,
            int index
    ) {
        if (index == lists.length) {
            return null;
        }

        LinkedList.Node last = findLastNode(lists[index]);
        last.next = combineRecursive(lists, index + 1);

        return lists[index];
    }

    private static LinkedList.Node findLastNode(LinkedList.Node head) {
        LinkedList.Node prev = null;

        while (head != null) {
            prev = head;
            head = head.next;
        }

        return prev;
    }
}
