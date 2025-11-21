package com.TPB.team_portal_backend.dto;

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

    // Was: private ImpactLevel impact;
    private String impact; // e.g. "High", "Medium", "Low", "Complex", etc.

    private String solution;

    // Was: private Status status;
    private String status;

    private String comments;

    private String typeOfAsk;

    // Was: private Category category;
    private String category;

    // Was: private Priority priority;
    private String priority;

    // Was: private Complexity complexity;
    private String complexity;

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
