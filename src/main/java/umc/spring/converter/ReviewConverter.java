package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;

// 12주차 사진도 같이 업로드하기
public class ReviewConverter {

    public static ReviewImage toReviewImage(String imageUrl, Review review){

        return ReviewImage.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();

    }

}
