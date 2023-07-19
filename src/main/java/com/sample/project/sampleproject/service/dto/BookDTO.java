package com.sample.project.sampleproject.service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private Integer publicationYear;
    private String genre;
    private String useYn;
}
