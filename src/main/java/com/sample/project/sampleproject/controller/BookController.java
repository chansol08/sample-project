package com.sample.project.sampleproject.controller;

import com.sample.project.sampleproject.service.BookService;
import com.sample.project.sampleproject.service.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/main")
    public String book() {
        return "book/book";
    }


    @GetMapping("/content")
    public ModelAndView getContents(@RequestParam String id) {
        ModelAndView mav = new ModelAndView();
        BookDTO bookDTO = bookService.findById(Long.parseLong(id));
        mav.addObject("content", bookDTO);
        mav.setViewName("book/content");
        return mav;
    }

    @ResponseBody
    @PostMapping(value = "/save", produces = "application/json")
    public boolean save(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @ResponseBody
    @GetMapping(value = "/list", produces = "application/json")
    public Page<BookDTO> getBookList(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return bookService.findBookByRange(page, size, "Y");
    }
}
