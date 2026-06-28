class TimeMap {

    // For each key, store a growing list of (timestamp, value), sorted by timestamp.
    private final Map<String, List<Entry>> store;

    // Simple pair class
    private static class Entry {
        final int t;
        final String v;
        Entry(int t, String v) { this.t = t; this.v = v; }
    }

    public TimeMap() {
        this.store = new HashMap<>();
    }
    
    // O(1) amortized: append a new (timestamp, value) for this key
    public void set(String key, String value, int timestamp) {
        List<Entry> list = store.computeIfAbsent(key, k -> new ArrayList<>());
        // Because timestamps are strictly increasing for a given key, appending keeps the list sorted
        list.add(new Entry(timestamp, value));
    }
    
    // O(log m): binary search the rightmost entry with t <= timestamp
    public String get(String key, int timestamp) {
        List<Entry> list = store.get(key);
        if (list == null || list.isEmpty()) return "";

        int lo = 0, hi = list.size() - 1;
        int ansIndex = -1; // index of the best candidate seen so far
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid).t <= timestamp) {
                ansIndex = mid;   // mid is a valid candidate; try to find a later one
                lo = mid + 1;
            } else {
                hi = mid - 1;     // need earlier timestamps
            }
        }
        return ansIndex == -1 ? "" : list.get(ansIndex).v;
    }
}