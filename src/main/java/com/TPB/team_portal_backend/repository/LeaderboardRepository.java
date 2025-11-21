package com.TPB.team_portal_backend.repository;

import com.TPB.team_portal_backend.model.Idea;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaderboardRepository extends IdeaRepository {

    // Example custom query: top liked ideas
    // @Query("SELECT i FROM Idea i ORDER BY i.likes DESC")
    // List<Idea> findTopLikedIdeas();
}
