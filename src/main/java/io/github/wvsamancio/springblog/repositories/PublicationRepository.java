package io.github.wvsamancio.springblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wvsamancio.springblog.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
