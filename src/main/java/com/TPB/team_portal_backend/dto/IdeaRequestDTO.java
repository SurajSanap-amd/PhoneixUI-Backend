package com.TPB.team_portal_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaRequestDTO {

    @NotBlank(message = "Email is required")
    private String email;

    private String name;

    @NotBlank(message = "Toil item is required")
    private String toilItem;  // Toil/Improvement Item

    private String description;

    private String impactedComponents;

    private String painPoints;

    private String frequency;

    private String manualEffort;

    private Boolean automatable;

    // Was: private ImpactLevel impact;
    private String impact; // free text now

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
}
