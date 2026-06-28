class Solution {
    public int leastInterval(char[] tasks, int n) {
        // 1) Count frequencies of tasks
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        // 2) Max-heap of remaining counts
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) if (f > 0) maxHeap.offer(f);

        int time = 0;

        // 3) Process in rounds/windows of size n + 1
        while (!maxHeap.isEmpty()) {
            // Holds tasks we ran this round and that still have remaining count
            List<Integer> nextRound = new ArrayList<>();

            int slots = n + 1; // number of distinct tasks we can run this round

            // Fill this round with up to n+1 different tasks
            while (slots > 0 && !maxHeap.isEmpty()) {
                int remaining = maxHeap.poll(); // pick the most frequent task
                remaining--;                    // run it once
                time++;                         // one CPU cycle consumed

                if (remaining > 0) {
                    nextRound.add(remaining);   // it will need to run again in future rounds
                }
                slots--;
            }

            // Push back tasks that still need to be run
            for (int r : nextRound) maxHeap.offer(r);

            if (maxHeap.isEmpty()) {
                // No more tasks; we don't add idle time at the tail
                break;
            }

            // If we couldn't fill the whole round, we must account for idles
            // e.g., ran k tasks (< n+1), but cooldown window is fixed → add (slots) idle cycles
            time += slots; // slots is how many we left unused in this (n+1)-window
        }

        return time;
    }
}