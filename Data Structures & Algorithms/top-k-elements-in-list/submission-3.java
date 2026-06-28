class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Count frequencies of each number
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. Create buckets based on frequency
        // The index of the array represents the frequency, and the list stores numbers with that frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 3. Place numbers into their respective frequency buckets
        for (int num : freqMap.keySet()) {
            int frequency = freqMap.get(num);
            buckets[frequency].add(num);
        }

        // 4. Collect the top K frequent elements by iterating buckets from highest frequency
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            for (int num : buckets[i]) {
                result.add(num);
                if (result.size() == k) {
                    break; // Found K elements, can stop early
                }
            }
        }

        // 5. Convert List to int array
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = result.get(i);
        }
        return topK;
    }
}