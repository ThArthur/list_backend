package com.rokku.list_app.dto.manhwa;

import com.rokku.list_app.dto.category.CategoryResponse;
import com.rokku.list_app.models.Manhwa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class ManhwaGetResponse {
    private Long id;
    private String title;
    private int chapterCount;
    private boolean isOngoing;
    private String author;
    private List<CategoryResponse> categories;

    public static ManhwaGetResponse toManhwa(Manhwa manhwa) {
        return ManhwaGetResponse.builder()
                .id(manhwa.getId())
                .title(manhwa.getTitle())
                .chapterCount(manhwa.getChapterCount())
                .isOngoing(manhwa.isOngoing())
                .author(manhwa.getAuthor())
                .categories(manhwa.getCategories().stream()
                        .map(CategoryResponse::toCategory)
                        .collect(Collectors.toList()))
                .build();
    }
}
