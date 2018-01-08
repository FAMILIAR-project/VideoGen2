package fr.istic.m2il.idm.videogen.repository;

import fr.istic.m2il.idm.videogen.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
