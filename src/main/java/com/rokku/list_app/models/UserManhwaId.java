package com.rokku.list_app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class UserManhwaId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long manhwaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserManhwaId that = (UserManhwaId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(manhwaId, that.manhwaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, manhwaId);
    }
}
