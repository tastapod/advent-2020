# Package day10

Here are the allowed options:

```
(0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
- []
(0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
- [11]
(0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
- [6]
(0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
- [6, 11]
(0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
- [5]
(0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
- [5, 11]
(0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
- [5, 6]
(0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
- [5, 6, 11]
```

So remove more and more elements _and remember which
ones you removed_, because any subset of those is a
legitimate chain.

Then lay a bitmask over all elements in the set. The
set of legitimate subsets is then all the possible values
of that bitmask, i.e. `2 ^ set.size`

What happens when two adjacent entries don't work properly?
```
(0), 1, 4, 5, 6, 8, 10, 11, 12, 15, 16, 19, (22)
- []
(0), 1, 4, 5, 6, 8, 10, 12, 15, 16, 19, (22)
- [11]
(0), 1, 4, 5, 8, 10, 11, 12, 15, 16, 19, (22)
- [6]
(0), 1, 4, 5, 8, 10, 12, 15, 16, 19, (22)
- [6, 11]
(0), 1, 4, 6, 8, 10, 11, 12, 15, 16, 19, (22)
- [5]
(0), 1, 4, 6, 8, 10, 12, 15, 16, 19, (22)
- [5, 11]
(0), 1, 4, 8, 10, 11, 12, 15, 16, 19, (22)
- [5, 6] <- Doesn't work!
(0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
- [5, 6, 11] <- Doesn't work!
```

So recurse to find legitimate lines, only from the current omission
onwards (eliminates duplicates), and collect the leaves.

Legitimate = p+1 - p-1 <= max_delta

Then it's sum(2^leaf.size).