from collections import deque

node_count, edge_count, start = map(int, input().split(" "))
connectList = [[] for _ in range(node_count + 1)]

def InputArguments():
    global edge_count
    for i in range(edge_count):
        nodeA, nodeB = map(int, input().split(" "))
        connectList[nodeA].append(nodeB)
        connectList[nodeB].append(nodeA)
    for list in connectList:
        list.sort()

def DFS():
    global start, connectList
    visited = [False for _ in range(len(connectList))]
    result = ""

    stack = [start]
    while len(stack) > 0:
        current = stack.pop()
        if visited[current] == False:
            visited[current] = True
            result += f'{current} '
            for node in list(reversed(connectList[current])):
                stack.append(node)
    
    print(result)

def BFS():
    global start, connectList
    visited = [False for _ in range(len(connectList))]
    result = ""

    queue = deque()
    queue.append(start)

    while len(queue) > 0:
        current = queue.popleft()
        if visited[current] == False:
            visited[current] = True
            result += f'{current} '
            for node in connectList[current]:
                if visited[node] == False:
                    queue.append(node)
        
    print(result)

InputArguments()
DFS()
BFS()