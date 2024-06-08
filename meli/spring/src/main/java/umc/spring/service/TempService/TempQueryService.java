package umc.spring.service.TempService;

// Service 작성하기
// Service를 작성할 때, 아래와 같은 규칙을 따른다.
/*
1. GET 요청과 나머지 요청에 대해 아래와 같이 분리한다.

	a. GET 요청에 대한 비즈니스 로직을 처리할 경우
			TempQueryService 이렇게 만든다.
	b. 나머지 요청에 대한 비즈니스 로직을 처리할 경우
			TempCommandService 이렇게 만든다.

2. 서비스를 만들 경우 인터페이스를 먼저 두고 이를 구체화 한다.
	 TempQueryService 인터페이스, TempCommandService 인터페이스를 만들고,
	 이에 대한 Impl 구체화 클래스를 만든다.

3. 컨트롤러는 인터페이스를 의존하며 실제 인터페이스에 대한 구체화 클래스는
	 Springboot의 의존성 주입을 이용한다!
 */
public interface TempQueryService {

    void CheckFlag(Integer flag);

}
