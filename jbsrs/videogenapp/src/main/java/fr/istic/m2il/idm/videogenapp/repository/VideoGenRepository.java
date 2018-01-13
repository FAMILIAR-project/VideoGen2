package fr.istic.m2il.idm.videogenapp.repository;

/**
 * @author ismael
 */

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface VideoGenRepository extends JpaRepository<VideoGen, Long> {
}
