package com.rokku.list_app.dto.category;

import com.rokku.list_app.models.Category;
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
public class CreateCategoryRequest {
    private String name;

    public Category toCategory() {
        return Category.builder()
                .id(null)
                .name(this.name)
                .manhwas(null)
                .build();
    }
}
