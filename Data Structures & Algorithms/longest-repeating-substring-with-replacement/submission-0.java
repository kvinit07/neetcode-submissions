class Solution {
    public int characterReplacement(String s, int k) {
      Map<Character, Integer> freq = new HashMap<>();
      int left = 0, maxFreq = 0, maxLen = 0;

      for (int right = 0; right < s.length(); right++) {
        char rc = s.charAt(right);
        freq.put(rc, freq.getOrDefault(rc,0) + 1);
        maxFreq = Math.max(maxFreq, freq.get(rc));

        while ((right - left + 1) - maxFreq > k) {
           char lc = s.charAt(left);
           freq.put(lc, freq.get(lc) - 1);
           left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
      }
      return maxLen;
    }
}
