package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Long save(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        BookEntity bookEntity = toSaveEntity(bookDTO);
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
//        System.out.println("bookEntity = " + bookEntity);
//        BookEntity savedEntity = bookRepository.save(bookEntity);
//        System.out.println("savedEntity = " + savedEntity);
        // 저장 처리 후 저장한 데이터의 id 값을 리턴
        return bookRepository.save(bookEntity).getId();
    }



//    private BookEntity toSaveEntity(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        return bookEntity;
//    }

    public List<BookDTO> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
//        List<BookEntity> -> List<BookDTO>
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity: bookEntityList) {
            /*
                1. Entity -> DTO 변환 메서드 호출(메서드는 BoardDTO에 정의)
                2. 호출 결과를 DTO 객체로 받음.
                3. DTO 객체를 DTO 리스트에 추가
                4. 반복문 종료 후 DTO 리스트를 리턴
            */
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            bookDTOList.add(bookDTO);
//            bookDTOList.add(BookDTO.toDTO(bookEntity));
        }
        return bookDTOList;
    }

    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            System.out.println("있다");
            // optional 객체에서 꺼내기
            BookEntity bookEntity = optionalBookEntity.get();
            // BookEntity -> BookDTO 변환
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            return bookDTO;

//            return BookDTO.toDTO(optionalBookEntity.get());
        } else {
            System.out.println("없다");
            return null;
        }
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toUpdateEntity(bookDTO);
        bookRepository.save(bookEntity);
    }
}

