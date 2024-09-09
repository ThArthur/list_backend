package com.rokku.list_app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
public class Manhwa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "chapter_count", nullable = false)
    private int chapterCount;

    @Column(name = "is_ongoing", nullable = false)
    private boolean isOngoing;

    @Column(nullable = false)
    private String author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "manhwa_category",
            joinColumns = @JoinColumn(name = "manhwa_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    List<Category> categories;

    @OneToMany(mappedBy = "manhwa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserManhwa> users;
}