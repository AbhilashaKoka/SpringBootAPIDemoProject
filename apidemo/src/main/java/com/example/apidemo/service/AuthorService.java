package com.example.apidemo.service;

import com.example.apidemo.model.Author;
import com.example.apidemo.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorService {
    private List<Author> listOfAuthor = new ArrayList<>(List.of(Author.builder()
                    .id(Integer.parseInt("1"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 1")
                    .lastName("Last Name 1").build(),
            Author.builder()
                    .id(Integer.parseInt("2"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 2")
                    .lastName("Last Name 2").build(),
            Author.builder()
                    .id(Integer.parseInt("3"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 3")
                    .lastName("Last Name 3").build(),
            Author.builder()
                    .id(Integer.parseInt("4"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 4")
                    .lastName("Last Name 4").build(),
            Author.builder()
                    .id(Integer.parseInt("5"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 5")
                    .lastName("Last Name 5").build(),
            Author.builder()
                    .id(Integer.parseInt("6"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 6")
                    .lastName("Last Name 6").build(),
            Author.builder()
                    .id(Integer.parseInt("7"))
                    .idBook(Integer.parseInt("1"))
                    .firstName("First Name 7")
                    .lastName("Last Name 7").build()));


    public List<Author> getListOfAuthor(){


        return listOfAuthor;
    }
    public Author getAuthorById(Integer id){

     return  listOfAuthor.stream()
             .filter(x->x.getId()==id).findFirst().get();
    }
    public List<Author> addAuthor(Author author){
        listOfAuthor.add(author);
        return listOfAuthor;
    }
    public Author updateAuthor(Integer id, Author author){
        return listOfAuthor.stream()
                .filter(x->x.getId()==id)
                .peek(o->o.setIdBook(author.getIdBook()))
                .peek(o->o.setFirstName(author.getFirstName()))
                .peek(o->o.setLastName(author.getLastName()))
                .findFirst().get();
    }
    public List<Author> deleteAuthor(Integer id){
        listOfAuthor.removeIf(x->x.getId()==id);
        return listOfAuthor;

    }

}
