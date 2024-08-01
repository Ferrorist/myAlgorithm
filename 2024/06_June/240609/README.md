# 2024.06.09 문제풀이

## 프로그래머스 12941. 최솟값 만들기
처음에는 배열 A와 배열 B를 list로 만드려고 하였으나,<br>
가장 크기가 큰 정수와 가장 크기가 작은 정수끼리 곱한 후,<br>
누적 합에 적용하는 것이 최솟값을 만드는 방법이라 생각하여<br>
두 배열을 오름차순으로 정렬한 후, 배열 A는 index 0부터 n까지, 배열 B는 index n (가장 오른쪽 끝)부터 0까지<br>
각각 배열의 원소 하나를 곱하여 누적 합에 적용한다.<br>
<br>
Tip) <br>
Arrays.sort()<br>
내림차순 시 Collections.reverseOrder()를 사용해야 하며, 이때 Wrapper 클래스를 활용하여야 함.

## 프로그래머스 12951. JadenCase 문자열 만들기

Tip) <br>
String → char[] : toCharArray() 사용 <br>
char, char[] → String : String.valueOf(char 변수) 사용 <br>


## 프로그래머스 12924. 숫자의 표현
처음에는 투 포인터를 생각하였으나, 문제의 특성 상 원소 10000개를(혹은 10001개) 모두 사용할 case는 거의 없다고 판단.<br>
메모리를 낭비할 것이라 판단하여 queue와 현재 합 (sum)을 이용하여 문제를 해결함.