class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) { 
        if (intervals == null || intervals.size() <= 1) return true;
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        int prevEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start < prevEnd) return false;
            prevEnd = curr.end;
        }
        return true;
    }
}
