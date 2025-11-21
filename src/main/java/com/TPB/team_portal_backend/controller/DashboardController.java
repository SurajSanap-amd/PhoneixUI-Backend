
package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.service.IdeaService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://phoneixui-amdocs.netlify.app"
})public class DashboardController {

    private final IdeaService service;

    public DashboardController(IdeaService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        var allIdeas = service.getAllItems();
        int totalIdeas = allIdeas.size();
        int totalLikes = allIdeas.stream().mapToInt(i -> Optional.ofNullable(i.getLikes()).orElse(0)).sum();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalIdeas", totalIdeas);
        stats.put("totalLikes", totalLikes);
        return stats;
    }
}
