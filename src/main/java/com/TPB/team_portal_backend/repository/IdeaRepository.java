package com.TPB.team_portal_backend.repository;

import com.TPB.team_portal_backend.model.Idea;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Primary
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
