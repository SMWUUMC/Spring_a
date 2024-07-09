package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    @Override
    public Optional<Member> findMember(Long id) {
        return null;
    }
}
