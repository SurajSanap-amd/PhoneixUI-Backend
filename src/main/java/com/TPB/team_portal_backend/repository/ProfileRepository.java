package com.TPB.team_portal_backend.repository;

import com.TPB.team_portal_backend.model.Idea;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfileRepository extends IdeaRepository {

    // In the future:
    // List<Idea> findByCreatedBy(String username);
}
