class Twitter {

    // Monotonic increasing timestamp used to order tweets globally.
    private int time;

    // followees[u] = set of users that 'u' follows.
    private final Map<Integer, Set<Integer>> followees;

    // userTweets[u] = head of the tweet linked list for user 'u' (most recent first).
    private final Map<Integer, TweetNode> userTweets;

    // Node representing a tweet in a singly-linked list for a user.
    private static class TweetNode {
        int tweetId;     // ID of the tweet
        int time;        // Global timestamp when posted
        TweetNode next;  // Next older tweet by the same user

        TweetNode(int tweetId, int time, TweetNode next) {
            this.tweetId = tweetId;
            this.time = time;
            this.next = next;
        }
    }

    /** Initialize your data structures. */
    public Twitter() {
        this.time = 0;
        this.followees = new HashMap<>();
        this.userTweets = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     * - O(1) time: prepend to the user's tweet list.
     */
    public void postTweet(int userId, int tweetId) {
        // Increment global time so each tweet has a strictly increasing timestamp.
        time++;

        // Prepend the tweet to the user's linked list (most recent first).
        TweetNode head = userTweets.get(userId);
        TweetNode newHead = new TweetNode(tweetId, time, head);
        userTweets.put(userId, newHead);

        // Lazily ensure user has an entry in followees map (optional but handy).
        followees.computeIfAbsent(userId, k -> new HashSet<>());
    }

    /**
     * Retrieve the 10 most recent tweet IDs in the user's news feed.
     * The news feed should retrieve tweets posted by the user and those they follow.
     * - Uses a max-heap (by time) to merge tweet lists from multiple users.
     * - O((F + K) log F) where:
     *     F = number of followed users + the user themselves
     *     K = up to 10 (tweets popped)
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>(10);

        // Collect candidate users: the user + everyone they follow.
        Set<Integer> sources = new HashSet<>();
        sources.add(userId);
        Set<Integer> fset = followees.getOrDefault(userId, Collections.emptySet());
        sources.addAll(fset);

        // Max-heap ordered by tweet time (most recent first).
        PriorityQueue<TweetNode> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.time, a.time) // descending by time
        );

        // Push the head tweet from each candidate user (if exists).
        for (int uid : sources) {
            TweetNode head = userTweets.get(uid);
            if (head != null) {
                maxHeap.offer(head);
            }
        }

        // Pop up to 10 most recent tweets overall.
        while (!maxHeap.isEmpty() && feed.size() < 10) {
            TweetNode node = maxHeap.poll();
            feed.add(node.tweetId);
            // Move to the next older tweet for that user.
            if (node.next != null) {
                maxHeap.offer(node.next);
            }
        }

        return feed;
    }

    /**
     * Follower follows a followee.
     * - O(1) average time.
     * - Following oneself is allowed in some designs; here we ignore self-follow to avoid duplication,
     *   because we already include the user in getNewsFeed.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            // No-op: we already include the user's own tweets in getNewsFeed.
            followees.computeIfAbsent(followerId, k -> new HashSet<>());
            return;
        }
        followees.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    /**
     * Follower unfollows a followee.
     * - O(1) average time.
     * - Unfollowing oneself is a no-op since self isn't stored in the set.
     */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = followees.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}