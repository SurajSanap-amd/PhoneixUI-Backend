package com.TPB.team_portal_backend.repository;

import com.TPB.team_portal_backend.model.Idea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IdeaRepositoryTest {

    @Autowired
    private IdeaRepository repo;

    @Test
    void testSaveAndFindIdea() {
        Idea item = Idea.builder()
                .startTime(LocalDateTime.now())
                .completionTime(LocalDateTime.now().plusHours(1))
                .email("test@amdocs.com")
                .name("Tester")
                .toilItem("DB Save Test")
                .description("Testing save operation")
                // these were enums earlier, now Strings
                .impact("LOW")
                .status("OPEN")
                .category("BUG")
                .priority("MEDIUM")
                .complexity("SIMPLE")
                .build();

        Idea saved = repo.save(item);
        assertThat(saved.getId()).isNotNull();

        Idea found = repo.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("test@amdocs.com");
    }
}
