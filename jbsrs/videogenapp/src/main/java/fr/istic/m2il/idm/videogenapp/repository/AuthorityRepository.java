package fr.istic.m2il.idm.videogenapp.repository;

import fr.istic.m2il.idm.videogenapp.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
