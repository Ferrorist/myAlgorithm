import sys
from collections import deque

# (y, x)
# up, right, down, left
DIR = [(-1, 0), (0, 1), (1, 0), (0, -1)] 
TOMATO = 1; WALL = -1

tomato_map = []; zero_count = 0
queue = deque()

def inputArguments():
    global tomato_map, zero_count, queue
    x, y = map(int, sys.stdin.readline().split(" "))    
    for i in range(y):
        tomato_map.append(list(map(int, sys.stdin.readline().split(" "))))
        for j in range(x):
            if tomato_map[i][j] == TOMATO:
                queue.append((i, j))
            elif tomato_map[i][j] != WALL:
                zero_count += 1


def process():
    global tomato_map, zero_count, queue
    if zero_count == 0:
        return 0
    
    days : int = 0
    while zero_count > 0 and len(queue) > 0:
        length : int = len(queue)
        for _ in range(length):
            cy, cx = queue.popleft()
            for dy, dx in DIR:
                ny, nx = cy + dy, cx + dx
                if checkRange(ny, nx) and tomato_map[ny][nx] == 0:
                    tomato_map[ny][nx] = TOMATO
                    zero_count -= 1
                    queue.append((ny, nx))

        days += 1

    if zero_count > 0:
        return -1
    else:
        return days

def checkRange(y: int, x: int):
    return 0 <= y < len(tomato_map) and 0 <= x < len(tomato_map[0])

inputArguments()
print(process())