package com.sample.project.sampleproject.repository;

import com.sample.project.sampleproject.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> findAllByUseYn(Pageable pageable, String useYn);
}
