package com.TPB.team_portal_backend.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AllIdeasRepository extends IdeaRepository {
    // Future extension: findByCategory, findByStatus, etc.
}
