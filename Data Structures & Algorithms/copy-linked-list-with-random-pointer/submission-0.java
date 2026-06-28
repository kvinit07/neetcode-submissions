// LeetCode provides this class:
// class Node {
//     int val;
//     Node next;
//     Node random;
//     public Node(int val) { this.val = val; }
// }

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1) Clone nodes and interleave: A -> A' -> B -> B' -> ...
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next; // move to original next
        }

        // 2) Assign random pointers for the cloned nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next; // A'.random = A.random'
            }
            curr = curr.next.next; // jump to next original node
        }

        // 3) Detach the copied list from the interleaved list
        curr = head;
        Node pseudoHead = new Node(0);
        Node copyCurr = pseudoHead;
        while (curr != null) {
            Node copy = curr.next;        // A'
            curr.next = copy.next;        // restore original link: A -> B
            copyCurr.next = copy;         // append copy: ... -> A'
            copyCurr = copy;
            curr = curr.next;             // move to next original: B
        }

        return pseudoHead.next;
    }
}