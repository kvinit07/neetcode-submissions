class Solution:
    def numDecodings(self, s: Optional[str]) -> int:
        if not s:
            return 0
        
        n = len(s)
        # nxt = dp[n] = 1 (empty string has one valid way to decode: do nothing)
        nxt = 1
        # nxt2 = placeholder; will become dp[n] after first slide
        nxt2 = 0
        curr = 0

        # Iterate from rightmost index to leftmost
        for i in range(n - 1, -1, -1):
            c = s[i]
            if c == '0':
                curr = 0
            else:
                # Take one digit: contributes dp[i+1] (which is nxt)
                curr = nxt
                # Try two digits if next char exists and pair is within 10..26
                if i + 1 < n:
                    d = s[i + 1]
                    # 10..19: (c == '1') or 20..26: (c=='2' and d<='6')
                    if c == '1' or (c == '2' and d <= '6'):
                        curr += nxt2  # add dp[i+2]
            # Slide the window for the next iteration (moving left)
            nxt2, nxt = nxt, curr

        return curr