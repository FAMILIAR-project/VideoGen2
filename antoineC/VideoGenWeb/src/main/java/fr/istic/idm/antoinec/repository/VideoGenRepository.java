package fr.istic.idm.antoinec.repository;

import fr.istic.idm.antoinec.domain.VideoGen;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the VideoGen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoGenRepository extends JpaRepository<VideoGen, Long> {

    @Query("select video_gen from VideoGen video_gen where video_gen.owner.login = ?#{principal.username}")
    List<VideoGen> findByOwnerIsCurrentUser();

}
