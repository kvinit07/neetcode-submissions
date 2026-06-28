class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        S = sum(nums)
        if (S + target) % 2 != 0:
            return 0
        P = (S + target) // 2
        if P < 0:
            return 0
        dp = [0] * (P + 1)
        dp[0] = 1
        for num in nums:
            for s in range(P, num - 1, -1):
               dp[s] += dp[s - num]
        return dp[P]    
        