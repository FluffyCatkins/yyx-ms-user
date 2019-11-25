package cn.yyx.msuser.service;

import cn.yyx.msuser.auth.Login;
import cn.yyx.msuser.domain.dto.LoginDTO;
import cn.yyx.msuser.domain.entity.User;
import cn.yyx.msuser.repository.UserRepository;
import cn.yyx.msuser.utils.JwtOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtOperator jwtOperator;
    @Login
    public User findById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("用户不存在"));
    }

    public String login(LoginDTO loginDTO) {
        return this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword()).map(user -> {
            HashMap<String,Object> claims = new HashMap<>();
            claims.put("username",user.getUsername());
            //claims.put("password",user.getPassword());
            claims.put("userId",user.getId());
            claims.put("role","super");
            return  jwtOperator.generateToken(claims);
        }).orElseThrow(
                ()->new IllegalArgumentException("账号密码不匹配！")
        );
    }
}
