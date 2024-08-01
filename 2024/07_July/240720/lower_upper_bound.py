import bisect

array = [1, 2, 4, 4, 5, 6, 8, 9]
target = 4

lower = bisect.bisect_left(array, target)
upper = bisect.bisect_right(array, target)

print("Lower Bound Index:", lower)
print("Upper Bound Index:", upper)
