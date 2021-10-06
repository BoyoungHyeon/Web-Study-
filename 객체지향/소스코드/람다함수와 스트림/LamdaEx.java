package 요소처리를위한람다식예제;

import java.util.*;
import java.util.stream.Stream;

public class LamdaEx {
    public static void main(String[] args) {
        List<Customer> list = (List) Arrays.asList(new Customer("박태호",1500),
                                                    new Customer("오수철",700));

        /*
            [중요] 컬렉션으로부터 스트림 얻기 => List 컬렉션의 참조변수를 통해서
         */
        Stream<Customer> stream = list.stream();
        stream.forEach(s -> {  // {} -  한문장 이상일 때는 중괄호를 생략하면 안된다.
            String name = s.getName();
            int score = s.getPoint_score();

            System.out.println(name + "=> " + "포인트 점수" + score + "점");
        });
    }
}