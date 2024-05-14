package com.example.hotrotinhthue.repository;

import com.example.hotrotinhthue.model.AnhMinhChung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinhChungrepo extends JpaRepository<AnhMinhChung,Long> {
    List<AnhMinhChung> findByTokhaithue(Long tokhaithueId);
}
