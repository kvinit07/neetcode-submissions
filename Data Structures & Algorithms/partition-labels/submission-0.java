class Solution {
    public List<Integer> partitionLabels(String s) {
      List<Integer> res = new ArrayList<>();
      if (s == null || s.length() == 0) return res;
      int[] last = new int[26];
      for (int i = 0; i < s.length(); i++) {
          char ch = s.charAt(i);
          last[ch - 'a'] = i;
      }
      int start = 0;
      int end = 0;
      for (int i = 0; i < s.length(); i++) {
         char ch = s.charAt(i);
         end = Math.max(end, last[ch-'a']);
         if (i == end) {
            res.add(end - start + 1);
            start = i  +  1;
         }
      }
      return res;
    }
}
