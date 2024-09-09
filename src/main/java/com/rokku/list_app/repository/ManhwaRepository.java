package com.rokku.list_app.repository;

import com.rokku.list_app.models.Manhwa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManhwaRepository extends JpaRepository<Manhwa, Long> {

}
