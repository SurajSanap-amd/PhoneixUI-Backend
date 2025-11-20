// package com.TPB.team_portal_backend.controller;

// import com.TPB.team_portal_backend.dto.IdeaRequestDTO;
// import com.TPB.team_portal_backend.dto.IdeaResponseDTO;
// import com.TPB.team_portal_backend.mapper.IdeaMapper;
// import com.TPB.team_portal_backend.model.Idea;
// import com.TPB.team_portal_backend.service.IdeaService;
// import org.springframework.core.io.InputStreamResource;
// import org.springframework.data.domain.Page;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.validation.annotation.Validated;
// import javax.validation.Valid;
// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/Ideas")
// @CrossOrigin(origins = "http://localhost:4200")
// @Validated
// public class IdeaController {

//     private final IdeaService service;

//     public IdeaController(IdeaService service) {
//         this.service = service;
//     }

//     // Get all Ideas
//     @GetMapping("/getAllIdeas")
//     public List<IdeaResponseDTO> getAllItems() {
//         return service.getAllItems().stream()
//                 .map(IdeaMapper::toResponseDTO) // Convert from Entity to DTO
//                 .collect(Collectors.toList());
//     }

//     // Create new Idea
//     @PostMapping("/createIdea")
//     public IdeaResponseDTO createItem(@RequestBody @Valid IdeaRequestDTO requestDTO) {
//         Idea createdIdea = service.saveItem(IdeaMapper.toEntity(requestDTO)); // Convert DTO to Entity
//         return IdeaMapper.toResponseDTO(createdIdea); // Return the response DTO
//     }

//     // Update existing Idea
//     @PutMapping("/updateIdea/{id}")
//     public IdeaResponseDTO updateItem(@PathVariable Long id, @RequestBody @Valid IdeaRequestDTO updatedItem) {
//         Idea updatedIdea = service.updateItem(id, IdeaMapper.toEntity(updatedItem));
//         return IdeaMapper.toResponseDTO(updatedIdea); // Return the response DTO
//     }

//     // Delete Idea
//     @DeleteMapping("/deleteIdea/{id}")
//     public void deleteItem(@PathVariable Long id) {
//         service.deleteItem(id);
//     }

//     // Get paginated Ideas
//     @GetMapping("/paged")
//     public Page<IdeaResponseDTO> getItemsPaged(
//             @RequestParam(defaultValue = "0") int page,
//             @RequestParam(defaultValue = "10") int size,
//             @RequestParam(defaultValue = "id") String sortBy,
//             @RequestParam(defaultValue = "asc") String direction
//     ) {
//         Page<Idea> pageResults = service.getItemsPaged(page, size, sortBy, direction);
//         return pageResults.map(IdeaMapper::toResponseDTO); // Convert each entity to DTO
//     }

//     // Like an Idea
//     @PutMapping("/{id}/like")
//     public IdeaResponseDTO likeIdea(@PathVariable Long id) {
//         Idea updatedIdea = service.incrementLike(id);
//         return IdeaMapper.toResponseDTO(updatedIdea); // Return the updated response DTO
//     }

//     // Export Ideas to Excel
//     @GetMapping("/exportIdeas")
//     public ResponseEntity<InputStreamResource> exportIdeas() throws IOException {
//         return service.exportIdeasToExcel();
//     }
// }
