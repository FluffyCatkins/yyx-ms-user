package cn.yyx.msuser.service;

import cn.yyx.msuser.domain.entity.User;
import cn.yyx.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User findById(Integer id){
        return this.userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("用户不存在"));
    }
}
