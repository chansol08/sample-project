package com.sample.project.sampleproject.mapper;

import com.sample.project.sampleproject.domain.BookEntity;
import com.sample.project.sampleproject.service.dto.BookDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T21:53:13+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(BookEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

        bookDTO.bookId( entity.getBookId() );
        bookDTO.title( entity.getTitle() );
        bookDTO.author( entity.getAuthor() );
        bookDTO.publicationYear( entity.getPublicationYear() );
        bookDTO.genre( entity.getGenre() );

        return bookDTO.build();
    }

    @Override
    public BookEntity toEntity(BookDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BookEntity.BookEntityBuilder bookEntity = BookEntity.builder();

        bookEntity.bookId( dto.getBookId() );
        bookEntity.title( dto.getTitle() );
        bookEntity.author( dto.getAuthor() );
        bookEntity.publicationYear( dto.getPublicationYear() );
        bookEntity.genre( dto.getGenre() );

        return bookEntity.build();
    }

    @Override
    public List<BookDTO> toDTOList(List<BookEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BookDTO> list = new ArrayList<BookDTO>( entityList.size() );
        for ( BookEntity bookEntity : entityList ) {
            list.add( toDTO( bookEntity ) );
        }

        return list;
    }

    @Override
    public List<BookEntity> toEntityList(List<BookDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BookEntity> list = new ArrayList<BookEntity>( dtoList.size() );
        for ( BookDTO bookDTO : dtoList ) {
            list.add( toEntity( bookDTO ) );
        }

        return list;
    }
}
