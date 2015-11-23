package fareye;

import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
* Created by fareye on 15/11/15.
*/
@Transactional
public interface TwitterRepository extends JpaRepository<Tweet,String> {
@Modifying
@Query(value="Select  t from Tweet t where t.username=?1 order by t.timeSt desc ")
    List<Tweet>getMessages(long username);
    //to get all the message !
    @Query(value="select u.username, u.name, t.timeSt, t.message, u.profileImgUrl from User u, Tweet t where t.username IN (select f.followsTo from Follow f where f.username=?1) AND u.username=t.username order by t.timeSt desc ")
    List<UserTemp> getCurrentMessages(long username);
    //handle in list of twitter obj in handler. and create pojo of all return value.

    @Query(value="Select count(t) from Tweet t where t.username=?1 ")
    long tweetCount(long username);

}
