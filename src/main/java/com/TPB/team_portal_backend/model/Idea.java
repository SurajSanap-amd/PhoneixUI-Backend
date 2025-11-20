package com.TPB.team_portal_backend.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.time.LocalDateTime;

import com.TPB.team_portal_backend.enums.Category;
import com.TPB.team_portal_backend.enums.Complexity;
import com.TPB.team_portal_backend.enums.ImpactLevel;
import com.TPB.team_portal_backend.enums.Priority;
import com.TPB.team_portal_backend.enums.Status;

@Data
@Entity
@Table(name = "IdeasTable")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime completionTime;

    @NotBlank
    private String email;

    private String name;

    @Column(length = 500)
    private String toilItem;  // Toil/Improvement Item

    @Lob
    private String description;

    @Column(length = 255)
    private String impactedComponents;

    @Lob
    private String painPoints;

    private String frequency;

    private String manualEffort;

    private Boolean automatable;

    @Enumerated(EnumType.STRING)
    private ImpactLevel impact; // High/Medium/Low

    @Lob
    private String solution;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    private String comments;

    private String typeOfAsk;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Complexity complexity;

    private Integer suggestedPriority;

    @Lob
    private String usDirection;

    private String ownerToCreateUS;

    private Boolean jiraCreated;
    private String jiraId; // optional ticket ID

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    //Like System
    @Column(nullable = false)
    private Integer likes = 0;


    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
