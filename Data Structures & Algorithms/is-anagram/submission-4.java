class Solution {
    public boolean isAnagram(String s, String t) {
       if (s.length() != t.length()) return false;
       Map<Character, Integer> freq = new HashMap<>();
       for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         freq.put(c, freq.getOrDefault(c,0) + 1);
       }
       for (int i = 0; i < t.length(); i++) {
         char c = t.charAt(i);
         Integer count = freq.get(c);
         if (count == null) return false;
         if (count == 1) {
          freq.remove(c);
         } else {
            freq.put(c, count-1);
         }
       }
       return freq.isEmpty();
    }
}
