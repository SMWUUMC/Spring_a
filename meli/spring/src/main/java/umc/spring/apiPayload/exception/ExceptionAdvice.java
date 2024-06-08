package umc.spring.apiPayload.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.ErrorReasonDTO;
import umc.spring.apiPayload.code.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
// Simple Logging Facade for Java
// - 다양한 로깅 프레임워크에 대한 추상화(인터페이스) 역할을 하는 라이브러리
// - 추상 로깅 프레임워크기 때문에 단독으로 사용하지 않는다
// - 즉, 최종 사용자가 배포시 원하는 로깅 프레임워크를 결정하고 사용해도
// SLF4J가 인터페이스화 돼있기 때문에,
// SLF4J를 의존하는 클라이언트 코드에서는 실제 구현을 몰라도 된다.
// (의존관계 역전 법칙)
// - 개발할 때, SLF4J API를 사용하여 로깅 코드를 작성
// - 배포할 때, 바인딩된 Logging Framework가 실제 로깅 코드를 수행
// SLF4J에서 제공하는 3가지 모듈
// 1. SLF4J Bridging Modules
// 2. SLF4J API (인터페이스)
// 3. SLF4J Binding (.jar)
// - 로깅이 필요한 부분에는 log 변수로 로그를 생성하면 된다.
// - 로깅 레벨은
// (많은 로깅) trace > warn > info > debug > error (적은 로깅)
// 순이다
@RestControllerAdvice(annotations = {RestController.class})
// = @ResponseBody + @ControllerAdvice
// - annotations 속성에 컨트롤러 클래스를 인수로 주면
// 적용되는 범위를 해당 컨트롤러 클래스로 한정할 수 있다.
// - 아무런 값도 주지 않으면 전역적으로 작동한다.

// - RestControllerAdvice는 @RestController가 붙은 대상에서
// Exception이 발생하는 것을 감지하는 역할을 한다.
// - 코드들을 보면 Service의 메소드를
// @RestController가 붙은 메소드에서 호출했기에,
// 결국 컨트롤러에서 Exception이 발생한 것으로 판단되는 것이다.
// - 마찬가지로 Converter에서 Exception을 발생시켜도
// 결국 컨트롤러에서 Converter를 호출하니 Exception Handler에 잡힌다.
// - 사실 RestControllerAdvice를 이용한 예외 처리는 실제로 사용을 해봐야,
// 이게 왜 편한지 비로소 체감이 됩니다.
// - 위의 방식을 사용하지 않으면 예외가 발생한 상황에서
// 코드가 매우 지저분해집니다.

// - 런타임에 발생한 Exception은
// MasterExceptionHandler가 감지하게 된다.
// - ExceptionAdvice가 @RestControllerAdvice를 가지고 있기 때문이다.
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
// GeneralException은 다시, RuntimeException을 상속받았기에,
// 이는 런타임에 발생한 Exception으로써
// MasterExceptionHandler가 감지하게 된다.
// ExceptionAdvice가 @RestControllerAdvice를 가지고 있기 때문이다.

    @org.springframework.web.bind.annotation.ExceptionHandler
    // - Controller 계층에서 발생하는 에러를 잡아서
    // 메서드로 처리해주는 기능이다
    // - @Controller로 선언된 클래스 안에서 해당 어노테이션으로
    // 메서드 안에서 발생할 수 있는 에러를 처리할 수 있다.
    // - 컨트롤러의 메소드, @ControllerAdvice나 @RestControllerAdvice가 있는
    // 클래스의 메소드에 @ExceptionHandler 어노테이션을 추가하여 에러를 처리한다.
    // - 발생한 예외는 ExceptionHandlerExceptionResolver에 의해 처리
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request){
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        /*
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
         */
        return handleExceptionInternalConstraint(
                e,
                ErrorStatus.valueOf(errorMessage),
                HttpHeaders.EMPTY,
                request
        );
    }

    //@Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors()//.stream()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(
                            fieldName,
                            errorMessage,
                            (existingErrorMessage, newErrorMessage)
                                    -> existingErrorMessage + ", " + newErrorMessage
                    );
                });

        /*
        private ResponseEntity<Object> handleExceptionInternalArgs(
            Exception e,
            HttpHeaders headers,
            ErrorStatus errorCommonStatus,
            WebRequest request,
            Map<String, String> errorArgs
    ) {
         */
        return handleExceptionInternalArgs(
                e,
                HttpHeaders.EMPTY,
                ErrorStatus.valueOf("_BAD_REQUEST"),
                request,
                errors
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();

        /*
        private ResponseEntity<Object> handleExceptionInternalFalse(
            Exception e,
            ErrorStatus errorCommonStatus,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request,
            String errorPoint
    ) {
         */
        return handleExceptionInternalFalse(e,
                ErrorStatus._INTERNAL_SERVER_ERROR,
                HttpHeaders.EMPTY,
                ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),
                request,
                e.getMessage()
        );
    }

    // - Exception 클래스들을 속성으로 받아 처리할 예외를 지정
    // - 예외 클래스를 지정하지 않는다면 파라미터에 설정된 에러 클래스를 처리
    // - @ExceptionHandler에 등록된 예외 클래스와
    // 파라미터로 받는 예외 클래스가 동일해야 함
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request){
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();

        /*
        private ResponseEntity<Object> handleExceptionInternal(
            Exception e,
            ErrorReasonDTO reason,
            HttpHeaders headers,
            HttpServletRequest request
    ) {
         */
        return handleExceptionInternal(generalException,
                errorReasonHttpStatus,
                null,
                request
        );
    }
    // ExceptionAdvice에서 GeneralException에 대해
    // 다시 한 번 오버로딩 된 함수 handleExceptionInternal를
    // 호출하고 있다.



    //public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request){
    private ResponseEntity<Object> handleExceptionInternal(
            Exception e,
            ErrorReasonDTO reason,
            HttpHeaders headers,
            HttpServletRequest request
    ) {
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);

        // e.printStackTrace();

        WebRequest webRequest = new ServletWebRequest(request);

        return super.handleExceptionInternal(
        // 코드를 까보면 handleExceptionInternal에서
        // 응답을 보내는 것을 확인할 수 있다.
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }
    // 최종적으로 onThrowException에서
    // 다시 한 번 호출된 함수 handleExceptionInternal에서는
    // 상속 받은 부모 클래스의 생성자를 호출하고 있다.
    // super.handleExceptionInternal의 코드를 까보면
    // 해당 부분에서 응답을 보내는 것을 확인할 수 있다.
    /*
    @Nullable
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

		if (request instanceof ServletWebRequest servletWebRequest) {
			HttpServletResponse response = servletWebRequest.getResponse();
			if (response != null && response.isCommitted()) {
				if (logger.isWarnEnabled()) {
					logger.warn("Response already committed. Ignoring: " + ex);
				}
				return null;
			}
		}

		if (body == null && ex instanceof ErrorResponse errorResponse) {
			body = errorResponse.updateAndGetBody(this.messageSource, LocaleContextHolder.getLocale());
		}

		if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR) && body == null) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		return createResponseEntity(body, headers, statusCode, request);
	}
     */

    //public ResponseEntity<Object> exception(Exception e, WebRequest request) {
    private ResponseEntity<Object> handleExceptionInternalFalse(
            Exception e,
            ErrorStatus errorCommonStatus,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request,
            String errorPoint
    ) {

        ApiResponse<Object> body = ApiResponse.onFailure(
                errorCommonStatus.getCode(),
                errorCommonStatus.getMessage(),
                errorPoint
        );

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );

    }

    /*
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

     */
    private ResponseEntity<Object> handleExceptionInternalArgs(
            Exception e,
            HttpHeaders headers,
            ErrorStatus errorCommonStatus,
            WebRequest request,
            Map<String, String> errorArgs
    ) {
        ApiResponse<Object> body = ApiResponse.onFailure(
                errorCommonStatus.getCode(),
                errorCommonStatus.getMessage(),
                errorArgs
        );

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

    //public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request){
    private ResponseEntity<Object> handleExceptionInternalConstraint(
    Exception e,
    ErrorStatus errorCommonStatus,
    HttpHeaders headers,
    WebRequest request
    ) {
        ApiResponse<Object> body = ApiResponse.onFailure(
                errorCommonStatus.getCode(),
                errorCommonStatus.getMessage(),
                null
        );

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

}
