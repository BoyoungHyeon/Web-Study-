

#  this변수, this()차이점 / super변수, super()차이점


### this변수, this()차이점

|구분|내용|사용 형식|
|------|---|---|
|this 변수|현재 객체의 주소(레퍼런스) - 즉 '객체자신'을 가리킨다. this는 컴파일러에 의해 자동으로 생성되며 별도로 this를 선언할 필요 없이 사용하면 된다.|this.객체내의멤버필드변수 = 매개변수;|
|this()|생성자에서 동일한 클래스 내의 다른 생성자를 호출할 때 사용한다. - 즉 한 클래스 내에서 한 생성자에서 다른 생성자를 호출할 때 사용|this(멤버필드변수 or 데이터값, . . . );|



##### this 변수 예제 - ThisEx2.java
```java
package this실습;

class Person2{
	private int juminNum;
	private int militaryNum;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	
	// 생성자
	public Person2(int juminNum,int militaryNum, int bYear, int bMonth, int bDay) {
		this.juminNum = juminNum;
		this.militaryNum = militaryNum;
		birthYear = bYear;
		birthMonth = bMonth;
		birthDay = bDay;
	}
	
	public Person2(int juminNum, int bYear, int bMonth, int bDay) {
		this.juminNum = juminNum;
		this.militaryNum = 0;
		birthYear = bYear;
		birthMonth = bMonth;
		birthDay = bDay;
	}
	
	public void showInfo() {
		System.out.println("주민번호 : " + juminNum);
		System.out.println("생년월일 : " + birthYear +"/"+birthMonth+"/"+birthDay);
		
		if(militaryNum != 0) {
			System.out.println("군번 : " +militaryNum + '\n');
		}else {
			System.out.println("군과 관계 없음\n");
		}
	}
}

public abstract class ThisEx2 {
	public static void main(String[] args) {
		Person man = new Person(961214,880102,1996,12,14);
		Person woman = new Person(990816,1990,11,16);
		
		man.showInfo();
		woman.showInfo();
		
	}

}
```

##### this() 예제 - ThisEx.java
```java
package this실습;

class Person{
	private int juminNum;
	private int militaryNum;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	
	// 생성자
	public Person(int juminNum,int militaryNum, int bYear, int bMonth, int bDay) {
		this.juminNum = juminNum;
		this.militaryNum = militaryNum;
		birthYear = bYear;
		birthMonth = bMonth;
		birthDay = bDay;
	}
	
	public Person(int juminNum, int bYear, int bMonth, int bDay) {
		this(juminNum, 0, bYear, bMonth, bDay);
		// 하나의 클래스에 여러 생성자가 있을 때, 다른 생성자를 호출할 때
	}
	
	public void showInfo() {
		System.out.println("주민번호 : " + juminNum);
		System.out.println("생년월일 : " + birthYear +"/"+birthMonth+"/"+birthDay);
		
		if(militaryNum != 0) {
			System.out.println("군번 : " +militaryNum + '\n');
		}else {
			System.out.println("군과 관계 없음\n");
		}
	}
}

public abstract class ThisEx {
	public static void main(String[] args) {
		Person man = new Person(961214,880102,1996,12,14);
		Person woman = new Person(990816,1990,11,16);
		
		man.showInfo();
		woman.showInfo();
		
	}
}
```
 	




### super변수, super()차이점

|구분|내용|사용 형식|
|------|---|---|
|super 변수|부모 객체를 참고하고 있기 때문에 부모 메소드를 직접 접근할 수 있다.|super.부모메소드( );|
|super()|자식 클래스의 생성자에서 부모 클래스 생성자 호출을 명시적으로 선택할 때 사용된다.|super(부모 클래스의 필드변수); super(부모 추상클래스의 필드변수); [주의할점]super()의 사용은 반드시 생성자 코드의 '첫 라인'에 와야 한다. 그렇지 않으면 컴파일 에러 발생함!|



##### 예제 - Airplane.java
```java
package super실습;

public class Airplane {
	public void land() {
		System.out.println("착륙합니다.");
	}
	
	public void fly() {
		System.out.println("일반 비행합니다.");
	}
	
	public void takeOff() {
		System.out.println("이륙합니다.");
	}
}
```

#####  SupersonicAirplane.java
```java
package super실습;

public class SupersonicAirplane extends Airplane{

	// 상수 선언
	public static final int NORMAL = 1; // 일반 비행 모드인 경우
	public static final int SUPERSONIC = 2; // 초음속 비행 모드인 경우
	
	public int flyMode = NORMAL;

	@Override
	public void fly() {
		super.fly();
		if(flyMode == SUPERSONIC) {
			System.out.println("초음속 비행합니다.");
		}else {
			super.fly();
		}
	}
	
}
```

#####  SupersonicAirplaneEx.java
```java
package super실습;

public class SupersonicAirplaneEx {
	public static void main(String[] args) {
		SupersonicAirplane sa = new SupersonicAirplane();
		
		sa.takeOff();
		sa.fly();
		sa.flyMode = SupersonicAirplane.SUPERSONIC;
		sa.fly();
		
		sa.flyMode = SupersonicAirplane.NORMAL;
		sa.fly();
		sa.land();
	}

}
```
