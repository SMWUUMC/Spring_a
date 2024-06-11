package umc.mini.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.mini.web.dto.MissionResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 미션 목록 조회 api 로직 처리
@Service
public class MissionService {

    public List<MissionResponse> getMissionsByStatus(String status) {
        // 더미 데이터 생성
        List<MissionResponse> dummyMissions = Arrays.asList(
                new MissionResponse(1L, "가게이름A", "12,000원 이상", "5%", "completed"),
                new MissionResponse(2L, "가게이름B", "15,000원 이상", "10%", "completed"),
                new MissionResponse(3L, "가게이름C", "20,000원 이상", "15%", "challenging"),
                new MissionResponse(4L, "가게이름D", "20,000원 이상", "15%", "challenging"),
                new MissionResponse(5L, "가게이름E", "20,000원 이상", "15%", "completed")
        );

        // 요청된 status와 일치하는 미션만 필터링하여 반환
        return dummyMissions.stream()
                .filter(mission -> mission.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
