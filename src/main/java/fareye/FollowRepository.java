package fareye;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fareye on 11/11/15.
 */

@Transactional
@Repository
public interface FollowRepository extends JpaRepository<Follow,String>
{
    User findByUsername(@Param("username") String username);
    @Modifying
    @Query(value="Delete from Follow f where f.username=?1 and f.followsTo=?2")
    void unfollow(long username,long followsto);

    @Query(value="Select count(f) from Follow f where f.username=?1 and f.username <>f.followsTo")
    long following(long username);

    @Query(value="Select count(f) from Follow f where f.followsTo=?1 and f.username <>f.followsTo")
    long follower(long username);

}
