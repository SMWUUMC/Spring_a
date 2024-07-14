package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StoreRequestDTO {

    @Getter
    // 9주차 2. 가게에 리뷰 추가하기 API
    // 12주차 사진도 같이 업로드하기
    @Setter
    public static class ReviewDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;

        // StoreRestController > createReview에서
        // 사진도 저장하도록 수정
        // 본래 기획은 사진의 List이지만 단건으로 해볼게요.
        //MultipartFile reviewPicture;
        List<MultipartFile> reviewPicture;
//        MultipartFile reviewPicture;
        // request Body에 이제 사진이 추가됩니다.
        // swagger에 가보면 reviewPicture가
        // String으로 표시가 됩니다
        // 그러나 목표는 사진 업로드이기 때문에
        // 의도와 다른 것을 확인 가능하죠
        // 이를 해결하기 위해 StoreRestController에
        // 한 가지 추가적인 설정이 필요합니다.
        // consumes = "multipart/form-data"
    }



    // 9주차 3. 가게에 미션 추가하기 API
    @Getter
    public static class MissionDTO{
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotBlank
        String missionSpec;
    }

}
