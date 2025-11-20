package com.TPB.team_portal_backend.service;

import com.TPB.team_portal_backend.model.Idea;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class IdeaService {

    private final DashboardService dashboardService;
    private final SubmitIdeaService submitIdeaService;
    private final AllIdeasService allIdeasService;
    private final LeaderboardService leaderboardService;
    private final ProfileService profileService;

    public IdeaService(
            DashboardService dashboardService,
            SubmitIdeaService submitIdeaService,
            AllIdeasService allIdeasService,
            LeaderboardService leaderboardService,
            ProfileService profileService
    ) {
        this.dashboardService = dashboardService;
        this.submitIdeaService = submitIdeaService;
        this.allIdeasService = allIdeasService;
        this.leaderboardService = leaderboardService;
        this.profileService = profileService;
    }

    // ---------- Dashboard ----------
    public Map<String, Object> getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

    // ---------- Submit Idea ----------
    public Idea saveItem(Idea idea) {
        return submitIdeaService.submitIdea(idea);
    }

    // ---------- All Ideas ----------
    public List<Idea> getAllItems() {
        return allIdeasService.getAllIdeas();
    }

    public Page<Idea> getItemsPaged(int page, int size, String sortBy, String direction) {
        return allIdeasService.getPagedIdeas(page, size, sortBy, direction);
    }

    public Idea updateItem(Long id, Idea updatedItem) {
        return allIdeasService.updateIdea(id, updatedItem);
    }

    public void deleteItem(Long id) {
        allIdeasService.deleteIdea(id);
    }

    public Idea incrementLike(Long id) {
        return allIdeasService.likeIdea(id);
    }

    public ResponseEntity<InputStreamResource> exportIdeasToExcel() throws IOException {
        return allIdeasService.exportIdeas();
    }

    // ---------- Leaderboard ----------
    public List<Idea> getTopIdeas(int limit) {
        return leaderboardService.getTopIdeas(limit);
    }

    // ---------- Profile ----------
    public List<Idea> getUserIdeas(String username) {
        return profileService.getUserIdeas(username);
    }
}
