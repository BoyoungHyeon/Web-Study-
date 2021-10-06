package 요소처리를위한람다식예제;

import java.util.*;

public class Customer {

    // 맴버변수
    private String name;        // 이름
    private int point_score;    //  포인트 점수

    // 생성자
    public Customer(String name, int point_score){
        this.name = name;
        this.point_score = point_score;
    }

    public String getName() {
        return name;
    }

    public int getPoint_score() {
        return point_score;
    }
}
