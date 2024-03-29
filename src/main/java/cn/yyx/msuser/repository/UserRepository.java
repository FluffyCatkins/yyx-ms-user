package cn.yyx.msuser.repository;

import cn.yyx.msuser.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUsernameAndPassword(String aaa, String sss);
}
