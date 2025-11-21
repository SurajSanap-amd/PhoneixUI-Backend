package com.TPB.team_portal_backend.controller;

import com.TPB.team_portal_backend.dto.IdeaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IdeaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateIdea() throws Exception {
        IdeaRequestDTO request = IdeaRequestDTO.builder()
                .email("veeresh@amd.com")
                .name("Veeresh Anumandla")
                .toilItem("E2E Parallel Execution")
                .description("Suite taking >1.5h")
                .impactedComponents("Service e2e")
                .painPoints("Data creation issue")
                .frequency("Per run")
                .manualEffort("2 sprints")
                .automatable(true)
                .impact("HIGH")                // changed to String
                .solution("Parallelize setup")
                .status("OPEN")                // changed to String
                .category("IMPROVEMENT")       // changed to String
                .priority("HIGH")              // changed to String
                .complexity("HIGH")            // changed to String
                .suggestedPriority(1)
                .usDirection("Move to regression")
                .ownerToCreateUS("Amar")
                .jiraCreated(true)
                .jiraId("JIRA-1234")
                .build();

        mockMvc.perform(post("/api/improvements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("veeresh@amd.com"))
                .andExpect(jsonPath("$.priority").value("HIGH"));
    }

    @Test
    void testGetAllIdeas() throws Exception {
        mockMvc.perform(get("/api/improvements"))
                .andExpect(status().isOk());
    }
}
