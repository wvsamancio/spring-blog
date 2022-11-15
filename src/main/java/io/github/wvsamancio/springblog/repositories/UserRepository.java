package io.github.wvsamancio.springblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wvsamancio.springblog.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {}
