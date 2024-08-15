package com.example.apidemo.controller;
import com.example.apidemo.model.Author;
import com.example.apidemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AuthorController {

   @Autowired
   private AuthorService authorService;


    @GetMapping("/Authors")
    public List<Author> getBookList(){
        return authorService.getListOfAuthor();
    }


    @GetMapping("/Authors/{id}")
    public Author getAuthor(@PathVariable Integer id){
        return authorService.getAuthorById(id);
    }

    @PostMapping("/Authors")
    public List<Author> postAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }


    @PutMapping("/Authors/{id}")
    public Author putAuthor(@RequestBody Author author,@PathVariable Integer id){
        return authorService.updateAuthor(id, author);
    }


    @DeleteMapping("/Authors/{id})")
    public List<Author> deleteAuthor(@PathVariable Integer id){
        return authorService.deleteAuthor(id);
    }
}


