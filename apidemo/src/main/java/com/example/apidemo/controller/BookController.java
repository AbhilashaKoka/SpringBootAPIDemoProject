package com.example.apidemo.controller;
import com.example.apidemo.model.Book;
import com.example.apidemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class BookController {

    @Autowired
    private BookService bookService;

//Entity

    @GetMapping("/Books")
    public List<Book> getBookList(){
        return bookService.getListOfBooks();

    }

    @GetMapping("/Books/{id}")
    public Book getBook(@PathVariable int id){
        return bookService.getBookById(id);
    }



    @PostMapping("/Books")
    public List<Book> postBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }


    @PutMapping("/Books/{id}")
    public Book putEmployee(@RequestBody Book book,@PathVariable int id){
        return bookService.updateBook(id, book);
    }


    @DeleteMapping("/Books")
    public List<Book> deleteBook(@PathVariable int id){
        return bookService.deleteBook(id);
    }




    }

