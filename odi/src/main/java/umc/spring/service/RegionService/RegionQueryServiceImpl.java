package umc.spring.service.RegionService;

import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository;

import java.util.Optional;

//1. 특정 지역에 가게 추가하기 API
public class RegionQueryServiceImpl implements RegionQueryService{

    private RegionRepository regionRepository;

    @Override
    public Optional<Region> findRegion(Long id){
        return regionRepository.findById(id);
    }
}
