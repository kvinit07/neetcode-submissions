class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       if (strs == null || strs.length == 0) return new ArrayList<>();
       Map<String, List<String>> groups = new HashMap<>();
       int[] count = new int[26];
       for (String s: strs) { 
         Arrays.fill(count, 0);
         for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch - 'a']++;
         }
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < 26; i++) {
            sb.append('#').append(count[i]);
         }
         String key = sb.toString();
         groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
       }
       return new ArrayList<>(groups.values());
    }
    }

