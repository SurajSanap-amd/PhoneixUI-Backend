package com.TPB.team_portal_backend.mapper;

import com.TPB.team_portal_backend.dto.IdeaRequestDTO;
import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
import com.TPB.team_portal_backend.model.Idea;

public class IdeaMapper {

    // Convert IdeaRequestDTO to Idea Entity
    public static Idea toEntity(IdeaRequestDTO requestDTO) {
        return Idea.builder()
                .email(requestDTO.getEmail())
                .name(requestDTO.getName())
                .toilItem(requestDTO.getToilItem())
                .description(requestDTO.getDescription())
                .impactedComponents(requestDTO.getImpactedComponents())
                .painPoints(requestDTO.getPainPoints())
                .frequency(requestDTO.getFrequency())
                .manualEffort(requestDTO.getManualEffort())
                .automatable(requestDTO.getAutomatable())
                .impact(requestDTO.getImpact())
                .solution(requestDTO.getSolution())
                .status(requestDTO.getStatus())
                .comments(requestDTO.getComments())
                .typeOfAsk(requestDTO.getTypeOfAsk())
                .category(requestDTO.getCategory())
                .priority(requestDTO.getPriority())
                .complexity(requestDTO.getComplexity())
                .suggestedPriority(requestDTO.getSuggestedPriority())
                .usDirection(requestDTO.getUsDirection())
                .ownerToCreateUS(requestDTO.getOwnerToCreateUS())
                .jiraCreated(requestDTO.getJiraCreated())
                .jiraId(requestDTO.getJiraId())
                .build();
    }

    // Convert Idea Entity to IdeaResponseDTO
    public static IdeaResponseDTO toResponseDTO(Idea idea) {
        return IdeaResponseDTO.builder()
                .id(idea.getId())
                .email(idea.getEmail())
                .name(idea.getName())
                .toilItem(idea.getToilItem())
                .description(idea.getDescription())
                .impactedComponents(idea.getImpactedComponents())
                .painPoints(idea.getPainPoints())
                .frequency(idea.getFrequency())
                .manualEffort(idea.getManualEffort())
                .automatable(idea.getAutomatable())
                .impact(idea.getImpact())
                .solution(idea.getSolution())
                .status(idea.getStatus())
                .comments(idea.getComments())
                .typeOfAsk(idea.getTypeOfAsk())
                .category(idea.getCategory())
                .priority(idea.getPriority())
                .complexity(idea.getComplexity())
                .suggestedPriority(idea.getSuggestedPriority())
                .usDirection(idea.getUsDirection())
                .ownerToCreateUS(idea.getOwnerToCreateUS())
                .jiraCreated(idea.getJiraCreated())
                .jiraId(idea.getJiraId())
                .startTime(idea.getStartTime())
                .completionTime(idea.getCompletionTime())
                .createdDate(idea.getCreatedDate())
                .updatedDate(idea.getUpdatedDate())
                .likes(idea.getLikes())
                .build();
    }
}
