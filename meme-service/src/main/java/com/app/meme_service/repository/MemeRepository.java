package com.app.meme_service.repository;

import com.app.meme_service.entity.MemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends JpaRepository<MemeEntity, Long> {
}
