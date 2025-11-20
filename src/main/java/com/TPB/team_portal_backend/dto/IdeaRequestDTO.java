package com.TPB.team_portal_backend.dto;

import com.TPB.team_portal_backend.enums.Complexity;
import com.TPB.team_portal_backend.enums.Category;
import com.TPB.team_portal_backend.enums.ImpactLevel;
import com.TPB.team_portal_backend.enums.Priority;
import com.TPB.team_portal_backend.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;


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

    // Removed createdDate and updatedDate because they are handled by the database on creation/update.
}


//Request DTO: This object would handle the data coming into the system. 
//It's used when creating or updating Idea objects. 
//This DTO would typically contain only the necessary fields for creating or modifying an idea.

//Response DTO: This object would be used to return data to the client. 
//It should contain the data that the client needs, but not necessarily all the fields from the Idea entity. 
//This helps in hiding internal details that the client doesn't need to know.