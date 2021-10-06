

#  열거형 Enum

### 열거형 Enum
 1. 클래스 처럼 보이게 하는 상수
 2. 서로 관련 있는 상수들끼리 모아 상수들을 대표할 수 있는 이름으로 타입을 정의하는 것
 3. Enum 클래스 형을 기반으로 한 클래스형 선언

 
### 열거형 Enum Enum을 사용하면서 우리가 얻을 수 있는 이점
 1. 코드가 단순해지며, 가독성이 좋다.
 2. 인스턴스 생성과 상속을 방지하여 상수값의 타입 안정성이 보장된다.
 3. enum class를 사용해 새로운 상수들의 타입을 정의함으로 정의한 타입이외의 타입을 가진 데이터값을 컴파일시 체크한다.
 4. 키워드 enum을 사용하기 때문에 구현의 의도가 열거형임을 분명하게 알 수 있다.
 

##### 예제 - PayGrop.java
```java
package Enum응용실습;

import java.util.*;
/*
    Arrays 클래스의 주요 메소드
        => static <T> List<T> asList(T..a)
            => 기능 : 전달받은 배열은 고정 크기의 List로 변환하여 변환해주는 메소드
 */

import java.lang.reflect.Array;

public enum PayGrop {
    CASH("현금", Arrays.asList("계좌이체","무통장입금","현장결제","토스")),
    CARD("카드",Arrays.asList("페이코","신용카드","카카오페이","배민페이")),
    ETC("기타",Arrays.asList("포인트","쿠폰")),
    EMPTY("결제종류없음",Collections.EMPTY_LIST);

    private String title;
    private List<String> payList;

    // 생성자
    PayGrop(String title, List<String> payList){
        this.title = title;
        this.payList = payList;
    }
}
```

##### PayGropTest.java
```java
package Enum응용실습;

import java.util.Scanner;

public class PayGropTest {

    private static final Object EMPTY = null;

    public static void main(String[] args) {
        for(PayGrop type : PayGrop.values()){
            System.out.print("결제 종류 : ");
            System.out.println(type.getTitle());
        }
        System.out.println();

        for(PayGrop type : PayGrop.values()){
            System.out.println(type.getPayList());
        }
        System.out.println();

        System.out.print("결제 수단을 입력하세요 >>> ");
        Scanner in = new Scanner(System.in);
        String payment_means = in.next();

        PayGrop pay = PayGrop.findByPayCode(payment_means);

        if(pay.equals(EMPTY)){
            System.out.println("결제 종류 : "+pay.getTitle());
        }else{
            System.out.println("결제 종류 : "+pay.getTitle());
        }
    }

}
```



