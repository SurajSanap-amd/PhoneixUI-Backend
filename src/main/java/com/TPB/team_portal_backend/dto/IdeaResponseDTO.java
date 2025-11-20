package com.TPB.team_portal_backend.dto;

import com.TPB.team_portal_backend.enums.Complexity;
import com.TPB.team_portal_backend.enums.Category;
import com.TPB.team_portal_backend.enums.ImpactLevel;
import com.TPB.team_portal_backend.enums.Priority;
import com.TPB.team_portal_backend.enums.Status;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaResponseDTO {

    private Long id;

    private String email;

    private String name;

    private String toilItem;  // Toil/Improvement Item

    private String description;

    private String impactedComponents;

    private String painPoints;

    private String frequency;

    private String manualEffort;

    private Boolean automatable;

    private ImpactLevel impact; // High/Medium/Low

    private String solution;

    private Status status;

    private String comments;

    private String typeOfAsk;

    private Category category;

    private Priority priority;

    private Complexity complexity;

    private Integer suggestedPriority;

    private String usDirection;

    private String ownerToCreateUS;

    private Boolean jiraCreated;
    private String jiraId; // optional ticket ID

    private LocalDateTime startTime;

    private LocalDateTime completionTime;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    // Like System
    private Integer likes;
}
