package com.TPB.team_portal_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

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

    // Was: @Enumerated(EnumType.STRING) private ImpactLevel impact;
    // Now free text
    @Column(length = 50)
    private String impact; // e.g. "High", "Medium", "Low", "Complex", etc.

    @Lob
    private String solution;

    // Was: @Enumerated(EnumType.STRING) private Status status;
    @Column(length = 50)
    private String status;

    @Lob
    private String comments;

    private String typeOfAsk;

    // Was: @Enumerated(EnumType.STRING) private Category category;
    @Column(length = 100)
    private String category;

    // Was: @Enumerated(EnumType.STRING) private Priority priority;
    @Column(length = 50)
    private String priority;

    // Was: @Enumerated(EnumType.STRING) private Complexity complexity;
    @Column(length = 50)
    private String complexity;

    private Integer suggestedPriority;

    @Lob
    private String usDirection;

    private String ownerToCreateUS;

    private Boolean jiraCreated;
    private String jiraId; // optional ticket ID

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Like System
    @Builder.Default
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
