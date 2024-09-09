package com.rokku.list_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserManhwaId.class)
public class UserManhwa {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "manhwa_id")
    private Long manhwaId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("manhwaId")
    @JoinColumn(name = "manhwa_id", insertable = false, updatable = false)
    private Manhwa manhwa;

    @Column(nullable = false)
    private boolean finished;

    @Column(nullable = true)
    private int actualChapter;

    @Column(nullable = true)
    private int rating;
}
