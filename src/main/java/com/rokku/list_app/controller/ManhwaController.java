package com.rokku.list_app.controller;

import com.rokku.list_app.dto.category.CategoryResponse;
import com.rokku.list_app.dto.manhwa.CreateManhwaRequest;
import com.rokku.list_app.dto.manhwa.ManhwaGetResponse;
import com.rokku.list_app.models.Manhwa;
import com.rokku.list_app.service.ManhwaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manhwas")
public class ManhwaController {

    @Autowired
    private ManhwaService manhwaService;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<ManhwaGetResponse>> getAllManhwa() {
        List<Manhwa> manhwaList = manhwaService.getAllManhwa();

        List<ManhwaGetResponse> responseList = manhwaList.stream()
                .map(ManhwaGetResponse::toManhwa)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ManhwaGetResponse> getManhwaById(@PathVariable Long id) {
        Optional<Manhwa> manhwa = manhwaService.getManhwaById(id);

        if (manhwa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ManhwaGetResponse manhwaGetResponse = ManhwaGetResponse.toManhwa(manhwa.get());

        return ResponseEntity.ok(manhwaGetResponse);
    }

    @PostMapping
    public ResponseEntity<ManhwaGetResponse> createManhwa(@RequestBody CreateManhwaRequest manhwaRequest) {
        Manhwa savedManhwa = manhwaService.createManhwa(manhwaRequest.toManhwa(), manhwaRequest.getCategories());

        ManhwaGetResponse manhwaGetResponse = ManhwaGetResponse.toManhwa(savedManhwa);

        return ResponseEntity.ok(manhwaGetResponse);
    }

}
