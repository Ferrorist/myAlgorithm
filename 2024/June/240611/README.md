# 2024.06.11 문제 풀이

## 프로그래머스 12911. 다음 큰 숫자
10진수 정수와 2진수 정수 등을 알아야 하는 문제.<br>
하지만 Java의 Wrapper 클래스인 Integer에서 이를 편하게 할 수 있는 method가 존재한다.<br><br>

Tip ) <br>
Integer.bitCount(n)<br>
10진수 정수 n을 2진수로 변환하였을 경우, true 비트의 개수 (1의 개수)<br>

## 프로그래머스 12973. 짝지어 제거하기
문제 풀 때, 전제 조건을 확인하지 않아 효율성 테스트에서 시간 초과가 발생하였다...<br>
<br>
이전 코드<br>
```java
class Solution
{
    public String[] selector = new String[26]; 
    public int solution(String s)
    {
        for(int i = 0; i < 26; i++){
            char c = (char)('a' + i);
            String str = String.valueOf(c) + String.valueOf(c);
            selector[i] = str;
        }
        
        while(true){
            int index = -1;
            for(int i = 0; i < 26; i++){
                index = s.indexOf(selector[i]);
                if(index != -1) {
                    s = s.replace(selector[i], "");
                    break;
                }
            }
            if(index == -1) break;
        }
        
        if(s.length() == 0)   return 1;
        return 0;
    }
}
```
이 문제의 전제 조건으로 문자열의 길이가 1,000,000 이하라는 점이다.<br>
위의 코드에서 사용한 indexOf method와 java에서 제공하는 contains method의 시간 복잡도를 알아보면,<br>
contains의 경우 내부에서 indexOf를 호출하여 그 값을 반환한다.<br>
```java
    public boolean contains(CharSequence s) {
        return indexOf(s.toString()) >= 0;
    }
``` 
그렇다면 indexOf는 어떨까?<br>
```java
@Native static final byte LATIN1 = 0;
@Native static final byte UTF16  = 1;

byte coder() {
    return COMPACT_STRINGS ? coder : UTF16;
}

public int indexOf(String str) {
    byte coder = coder();
    if (coder == str.coder()) {
        return isLatin1() 
        ? StringLatin1.indexOf(value, str.value)
        : StringUTF16.indexOf(value, str.value);
    }
    if (coder == LATIN1) {  // str.coder == UTF16
        return -1;
    }
    return StringUTF16.indexOfLatin1(value, str.value);
}
```
```java
    // StringLatin1.indexOf(value, str.value) 호출 시 다음 함수를 사용
    public static int indexOf(byte[] value, int valueCount, byte[] str, int strCount, int fromIndex) {
        byte first = str[0];
        int max = (valueCount - strCount);
        for (int i = fromIndex; i <= max; i++) {
            // Look for first character.
            if (value[i] != first) {
                while (++i <= max && value[i] != first);
            }
            // Found first character, now look at the rest of value
            if (i <= max) {
                int j = i + 1;
                int end = j + strCount - 1;
                for (int k = 1; j < end && value[j] == str[k]; j++, k++);
                if (j == end) {
                    // Found whole string.
                    return i;
                }
            }
        }
        return -1;
    }
```
결과적으로 탐색하는 문자열의 길이가 n, 찾으려는 문자열의 길이가 m이라면,<br>
순차적으로 탐색하므로 시간복잡도는 O(m * n) 이다.<br>
<br>
그러므로, 해당 문제에서 indexOf을 사용할 경우 시간 초과가 발생할 수 있다.<br>
그래서 Stack을 활용하였다.