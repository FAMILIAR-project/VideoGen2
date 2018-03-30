package fr.istic.m2il.idm.videogenapp.repository;

import fr.istic.m2il.idm.videogenapp.domain.VideoGen;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the VideoGen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoGenRepository extends JpaRepository<VideoGen, Long> {

}
