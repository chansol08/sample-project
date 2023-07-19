package com.sample.project.sampleproject.service;

import com.sample.project.sampleproject.domain.BookEntity;
import com.sample.project.sampleproject.mapper.BookMapper;
import com.sample.project.sampleproject.repository.BookRepository;
import com.sample.project.sampleproject.repository.BookRepositoryImpl;
import com.sample.project.sampleproject.service.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryImpl bookRepositoryImpl;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * 책의 제목으로 검색 시 해당 조건의 책들만 조회
     *
     * @param page
     * @param size
     * @param title
     * @param useYn
     * @return
     */
    public Page<BookDTO> findByTitle(int page, int size, String title, String useYn) {
        // @todo 검색 박스에서 조회한 제목에 해당하는 리스트를 가져온다.
        return null;
    }

    /**
     * key 값인 id 로 해당 책을 조회해 해당 데이터를 삭제
     *
     * @param id    조회할 데이터의 key 값
     * @param useYn 삭제 여부
     * @return save(bookDTO) 삭제 여부 변경 후 db 에 저장
     */
    public boolean deleteContents(long id, String useYn) {
        // @todo id를 검색해서 데이터가 있을 경우 삭제, 없을 경우 존재하지 않음으로 return false
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return false;
        }

        BookDTO bookDTO = bookMapper.toDTO(book.get());
        bookDTO.setUseYn(useYn);

        return save(bookDTO);
    }

    /**
     * 모든 도서 정보를 페이지 단위로 가져온다.
     *
     * @param page 페이지 번호 (0부터 시작)
     * @param size 한 페이지에 포함될 도서 정보의 수
     * @return 페이지 정보에 따른 도서 정보
     */
    public Page<BookDTO> findBookByRange(int page, int size, String useYn) {
        log.info("모든 도서 정보를 페이지 단위로 호출");
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepository.findAllByUseYn(pageable, useYn);

        List<BookDTO> bookList = new ArrayList<>();
        for (BookEntity book : bookPage.getContent()) {
            bookList.add(bookMapper.toDTO(book));
        }

        return new PageImpl<>(bookList, pageable, bookPage.getTotalElements());
    }

    /**
     * id 를 매개변수로 받아 책의 정보를 db 에서 가지고 온다.
     *
     * @param id 책의 key 값
     * @return null || bookDTO
     */
    public BookDTO findById(long id) {
        Optional<BookEntity> result = bookRepository.findById(id);

        if (result.isEmpty()) {
            log.info("조회된 결과가 없습니다.");
            return null;
        } else {
            return bookMapper.toDTO(result.get());
        }
    }

    /**
     * 전송객체인 bookDTO 를 받아 entity 로 변환 후 db 에 저장
     *
     * @param bookDTO 전송객체
     * @return true || false
     */
    @Transactional
    public boolean save(BookDTO bookDTO) {
        log.info("도서를 등록/수정 한다.");
        try {
            bookRepository.save(bookMapper.toEntity(bookDTO));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
