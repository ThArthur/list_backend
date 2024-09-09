package com.rokku.list_app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddManhwaToUserList {
    private Long manhwaId;
    private Boolean finished;
    private Integer rating;
    private String notes;
}
