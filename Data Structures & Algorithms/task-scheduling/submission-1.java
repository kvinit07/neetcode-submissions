class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) if (f > 0) maxHeap.offer(f);
        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> nextRound = new ArrayList<>();
            int slots = n + 1;
            while (slots > 0 && !maxHeap.isEmpty()) {
                int remaining = maxHeap.poll(); 
                remaining--;                   
                time++;                         
                if (remaining > 0) {
                    nextRound.add(remaining); 
                }
                slots--;
            }
            for (int r : nextRound) maxHeap.offer(r);
            if (maxHeap.isEmpty()) {
                break;
            }
            time += slots;
        }
        return time;
    }
}