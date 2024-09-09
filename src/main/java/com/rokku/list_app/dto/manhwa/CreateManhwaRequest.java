package com.rokku.list_app.dto.manhwa;

import com.rokku.list_app.models.Manhwa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class CreateManhwaRequest {
    private String title;
    private int chapterCount;
    private boolean isOngoing;
    private String author;
    private Set<Long> categories;

    public Manhwa toManhwa() {
        return Manhwa.builder()
                .title(this.title)
                .chapterCount(this.chapterCount)
                .isOngoing(this.isOngoing)
                .author(this.author)
                .build();
    }
}
