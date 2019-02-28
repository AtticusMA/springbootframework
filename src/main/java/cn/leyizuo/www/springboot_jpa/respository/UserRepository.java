package cn.leyizuo.www.springboot_jpa.respository;

import cn.leyizuo.www.springboot_jpa.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findByName(String name);
}