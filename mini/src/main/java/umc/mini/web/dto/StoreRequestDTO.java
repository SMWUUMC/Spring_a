package umc.mini.web.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class ReveiwDTO{

        // 리뷰 내용
        String body;

        // 리뷰 평점
        Float score;
    }
}
