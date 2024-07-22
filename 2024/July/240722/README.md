## 2024-07-22

### Java String 관련 함수들

1. String.contains(CharSequence chars) <br>
대상 문자열에 특정 문자열이 포함되어 있는지 확인하는 함수이며, 대/소문자를 구별한다.<br>
문자열의 길이를 $n$, 찾으려는 문자열의 길이를 $m$이라 하였을 때,<br>
문자열의 각 문자에서 시작해 $m$ 길이의 문자열이 있는지 검사하므로<br>
시간 복잡도는 $O(n * m)$ 이라 할 수 있다.


2. String.startsWith() <br>
문자열이 특정 문자열로 시작하는지를 확인한다.<br>
찾으려는 문자열의 길이가 $m$이라면, 문자열의 앞에서부터 길이 $m$만큼만 비교하므로,<br>
시간 복잡도는 $O(m)$ 이다.

3. String.subString() <br>
문자열의 일부분을 추출한다.<br>
시간 복잡도의 경우, 사용하는 Java 버전에 따라 달라진다.<br><br>
Java 6 이전의 경우, String 객체를 생성하지만 실제 'char' 배열을 복사하지 않고 원본 문자열의 'char' 배열을 참조한다.<br>
이 방식은, 메모리 효율성을 높여주지만, 원본 문자열이 커질 경우 메모리 누수 문제가 발생할 수 있다.<br>이 경우 시간복잡도는 $O(1)$ 이다.<br><br>
Java 7 이후부터는 원본 문자열의 'char' 배열을 공유하지 않고 새로운 'char' 배열을 생성하여 부분 문자열을 저장한다. 이는 메모리 누수 문제를 해결하기 위함이다.<br>
위의 방법으로는 beginIndex를 $m$, endIndex를 $k$라고 할 때, 새로운 배열을 생성하고, $k - m$ 길이의 부분 문자열을 복사해야 하므로, $O(k - m)$ 복사해야 하는 길이가 $n$이라면 $O(n)$이라 할 수 있다.<br><br>
substring 메소드는 두 가지 형태를 제공하고 있으며, Index는 음수도 적용할 수 있다.

    * public String substring(int startIndex)
    * public String substring(int startIndex, int endIndex)