package fareye;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fareye on 11/11/15.
 */

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,String>{

    User findByEmail(@Param("email") String email);
    User findByUsername(@Param("username") long username);
    User findByPhone(@Param("phone") String phone);
    User findByPhoneOrEmail(@Param("phone") String phone,@Param("email") String email);

    @Modifying
    @Transactional

    @Query(value = "update User u set u.password=?1 where u.email=?2")
    void resetUserPassword(String newPassword,String email);

    @Query("select u.name ,u.username,u.profileImgUrl from User u where u.username IN (select f.followsTo from Follow f where f.username=?1 and f.followsTo <> f.username)")
    List <User> whomIFollow(long myUsername);

    @Query("select u.name,u.username,u.profileImgUrl from User u where u.username NOT IN (select f.followsTo from Follow f where f.username=?1) And u.username <> ?1")
    List <User> tobefollowed(long myUsername);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.profileImgUrl=?1 where u.username=?2")
    void updateProfileImage(String imageUrl,long username);
}
