class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        // 1) Create all copies and map original -> copy
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // 2) Assign next and random using the map
        curr = head;
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next = map.get(curr.next);       // null-safe: map.get(null) == null
            copy.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }
}