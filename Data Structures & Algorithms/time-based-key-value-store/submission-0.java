class TimeMap {
    private Map<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        // Initialize TreeMap if key is new
        map.putIfAbsent(key, new TreeMap<>());
        // Store the value with its timestamp
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        // If key doesn't exist, return ""
        if (!map.containsKey(key)) return "";

        // Get the TreeMap for the key
        TreeMap<Integer, String> tree = map.get(key);

        // Find the greatest timestamp <= given timestamp
        Map.Entry<Integer, String> entry = tree.floorEntry(timestamp);

        // If no such timestamp exists, return ""
        return entry != null ? entry.getValue() : "";
    }
}
