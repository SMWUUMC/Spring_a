package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Uuid;
import umc.spring.repository.*;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 9주차 3. 가게에 미션 추가하기 API
    private final MissionRepository missionRepository;

    // 12주차 사진도 같이 업로드하기
    private final AmazonS3Manager s3Manager;
    // 서비스에서 AmazonS3Manager를 추가합시다.
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;
    // 기획 자체가 사진 여러장을 업로드 가능했기 때문에
    // review와 업로드가 되는 테이블을 연결 지었습니다.
    // 따라서 reveiw Image를 만들어야 하며 이 과정에서
    // 이미지를 업로드 해야합니다.



    // 9주차 2. 가게에 리뷰 추가하기 API
    // 12주차 사진도 같이 업로드하기
    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request){



//        Review review = StoreConverter.toReview(request);
//
//        String uuid = UUID.randomUUID().toString();
//        Uuid savedUuid = uuidRepository.save(Uuid.builder()
//                .uuid(uuid).build());
//
//        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), request.getReviewPicture());
//
//        review.setMember(memberRepository.findById(memberId).get());
//        review.setStore(storeRepository.findById(storeId).get());
//
//
//        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl,review));
//        return reviewRepository.save(review);



//        Review review = StoreConverter.toReview(request);
//        review.setMember(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Invalid member ID")));
//        review.setStore(storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Invalid store ID")));
//
//        if (request.getReviewPicture() != null && !request.getReviewPicture().isEmpty()) {
//            String uuid = UUID.randomUUID().toString();
//            Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
//            String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), request.getReviewPicture());
//            reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
//        }
//
//        return reviewRepository.save(review);



        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        List<ReviewImage> reviewImages = request.getReviewPicture().stream()
                .map(picture -> {
                    String uuid = UUID.randomUUID().toString();
                    Uuid savedUuid = uuidRepository.save(
                            Uuid.builder()
                            .uuid(uuid)
                            .build()
                    );
                    // Uuid 엔티티를 만들어

                    String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), picture);
                    // s3Manager.generateReviewKeyName(savedUuid)
                    // 이를 가지고 파일에 대한 KeyName을 만들고
                    //파일을 업로드 후

                    return ReviewConverter.toReviewImage(pictureUrl, review);
                    // 파일의 URL을 ReviewImage 엔티티에 담아서 저장하고 있습니다.
                })
                .collect(Collectors.toList());

        reviewImageRepository.saveAll(reviewImages);

        return reviewRepository.save(review);

    }



    // 9주차 3. 가게에 미션 추가하기 API
    @Override
    public Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request){

        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());

        return missionRepository.save(mission);

    }

}
