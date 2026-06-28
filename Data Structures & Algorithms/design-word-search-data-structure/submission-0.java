class WordDictionary {
    static class Node {
        Node[] children = new Node[26];
        boolean isWord;
    }

    private final Node root = new Node();

    public WordDictionary() { }

    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) cur.children[idx] = new Node();
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int idx, Node node) {
        if (node == null) return false;
        if (idx == word.length()) return node.isWord;

        char ch = word.charAt(idx);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && dfs(word, idx + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(word, idx + 1, node.children[ch - 'a']);
        }
    }
}
