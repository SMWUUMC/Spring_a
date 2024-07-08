package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Member 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)를
// 서비스 단에서 할까요?
// 7주차에서 설명했지만, 프로젝트마다 다릅니다.
// 저의 경우 서비스는 오로지 비즈니스 로직에만 집중을 합니다.
// 따라서 Member를 만드는 작업을 converter에서 하겠습니다.
public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Member 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)
    public static Member toMember(MemberRequestDTO.JoinDTO request) {

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                // 리스트는 new ArrayList<>() 이렇게
                // 초기화를 해줘야 합니다
                .build();

    }
    // 저는 여기서, 멤버 엔티티에 나이를 넣지 않음을 깨달았습니다
    // 그냥.. 진행 하겠습니다.
    // 여러분들은 원하시면 엔티티에 추가하시면 됩니다!



    // 10주차 1. 내가 작성한 리뷰 목록 API
    public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){

        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();

    }

    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();

    }

    // 10주차 3. 내가 진행중인 미션 목록 API
    public static MemberResponseDTO.MemberMissionPreViewDTO memberMissionPreViewDTO(MemberMission memberMission){

        return MemberResponseDTO.MemberMissionPreViewDTO.builder()
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus())
                .ownerStorename(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();

    }

    public static MemberResponseDTO.MemberMissionPreViewListDTO memberMissionPreViewListDTO(Page<MemberMission> memberMissionList){

        List<MemberResponseDTO.MemberMissionPreViewDTO> memberMissionPreViewDTOList = memberMissionList.stream()
                .map(MemberConverter::memberMissionPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.MemberMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDTOList.size())
                .memberMissionList(memberMissionPreViewDTOList)
                .build();

    }

}
