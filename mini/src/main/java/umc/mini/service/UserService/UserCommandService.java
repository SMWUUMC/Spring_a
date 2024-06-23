package umc.mini.service.UserService;

import umc.mini.domain.User;
import umc.mini.web.dto.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDto request);
}
