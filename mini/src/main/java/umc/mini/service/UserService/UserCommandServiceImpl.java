package umc.mini.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mini.api.code.exception.handler.FoodCategoryHandler;
import umc.mini.api.code.status.ErrorStatus;
import umc.mini.converter.UserConverter;
import umc.mini.converter.UserPreferConverter;
import umc.mini.domain.FoodCategory;
import umc.mini.domain.User;
import umc.mini.domain.mapping.UserPrefer;
import umc.mini.repository.FoodCategoryRepository;
import umc.mini.repository.UserRepository;
import umc.mini.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserPrefer> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
