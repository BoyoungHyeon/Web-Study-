# SHA-256 해싱(Hashing)알고리즘으로 패스워드 암호화하기

## SHA-256  
* SHA-256은 SHA(Secure Hash Algorithm) 알고리즘의 한 종류로서 256비트로 구성되며 64자리 문자열을 반환한다.
* SHA-256은 미국의 국립표준기술연구소(NIST; National Institute of Standards and Technology)에 의해 공표된 표준 해시 알고리즘인 SHA-2 계열 중 하나이며 블록체인에서 가장 많이 채택하여 사용하고 있다.
* 이름에 내포되어 있듯 {\displaystyle 2^{256}}{\displaystyle 2^{256}}만큼 경우의 수를 만들수 있다. 
* 개인용 컴퓨터로 무차별 대입을 수행해 해시 충돌 사례를 찾으려고 할 때 많은 시간이 소요될 정도로 큰 숫자이므로 충돌로부터 비교적 안전하다고 평가된다.

![image](https://user-images.githubusercontent.com/49936027/140029454-5fbded45-34c8-41e6-a215-c228cd7dc8b2.png)

![image](https://user-images.githubusercontent.com/49936027/140029494-e2052632-7e8d-4660-811b-d4c286808c69.png)

http://wiki.hash.kr/index.php/SHA256

### 암호화 알고리즘 패스워드 암호화 예제
* 자바에서 SHA 알고리즘을 사용하려면 MessageDigest 클래스를 이용한다. update() 메소드를 호출할 때마다 객체 내에 저장된 digest 값이 계속해서 갱신된다.
* 최종적으로 digest() 메소드를 호출하면 그 값을 가져올 수 있다.
 
#### PasswordEncryptUtil.java
```java
package 패스워드암호화시키기;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptUtil {

	public static void main(String[] args) {
		// 패스워드 지정
		String plainText = "test1234";	// 평문
		String sha256 = "" ;
		
		try {
			/* MessageDigest 클래스의 getInstance() 메소드의 매개변수에 "SHA-256" 알고리즘 이름을 지정함으로써 
				해당 알고리즘에서 해시값을 계산하는 MessageDigest를 구할 수 있다 */
			MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
			
			// 데이터(패스워드 평문)를 한다. 즉 '암호화'와 유사한 개념
			mdSHA256.update(plainText.getBytes("UTF-8"));
			
			// 바이트 배열로 해쉬를 반환한다.
			byte[] sha256Hash = mdSHA256.digest();
			
			// StringBuffer 객체는 계속해서 append를 해도 객체는 오직 하나만 생성된다. => 메모리 낭비 개선
			StringBuffer hexSHA256hash = new StringBuffer();
			
			// 256비트로 생성 => 32Byte => 1Byte(8bit) => 16진수 2자리로 변환 => 16진수 한 자리는 4bit
			for(byte b : sha256Hash) {
				String hexString = String.format("%02x", b);
				hexSHA256hash.append(hexString);
			}
			sha256 = hexSHA256hash.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}System.out.println(sha256);
		//937e8d5fbb48bd4949536cd65b8d35c426b80d2f830c5c308e2cdec422ae2244
	}
}
```


#### 실행 결과
![image](https://user-images.githubusercontent.com/49936027/140029755-a0f0b6ea-da7c-403b-82a1-e0758e4d6e91.png)

 
 ![image](https://user-images.githubusercontent.com/49936027/140029769-8cc5f6f8-a6d6-4730-909c-18f75078a4cc.png)
 
 암호화한 패스워드를 DB 회원 테이블에 담았다.

 

 
 
