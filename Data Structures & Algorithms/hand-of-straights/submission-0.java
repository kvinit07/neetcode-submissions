class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
      if (hand.length % groupSize != 0) return false;
      TreeMap<Integer, Integer> tree = new TreeMap<>();
      for (int fill : hand) {
         tree.put(fill, tree.getOrDefault(fill, 0) + 1);
      }
      while (!tree.isEmpty()) {
      int start = tree.firstKey();
      for (int val = start; val < start + groupSize; val++) {
         Integer count = tree.get(val);
         if (count == null) return false;
         if (count == 1) {
            tree.remove(val);
         } else {
            tree.put(val, count - 1);
         }
      }
      }
      return true;
    }
}
