package com.example.apidemo.service;
import com.example.apidemo.model.Book;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private List<Book> listOfBooks = new ArrayList<>(List.of(Book.builder()
                    .id(1)
                    .isbn( "9781449325862")
                    .title("Git Pocket Guide")
                    .subTitle("A Working Introduction")
                    .author("Richard E. Silverman")
                    .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
                    .publisher("O'Reilly Media")
                    .pages(234)
                    .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
                    .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build(),
            Book.builder()
                    .id(2)
                    .isbn( "9781449325868")
                    .title("Git Pocket Guide")
                    .subTitle("A Working Introduction")
                    .author("Richard E. Silverman")
                    .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
                    .publisher("O'Reilly Media")
                    .pages(234)
                    .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
                    .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build()));


    public List<Book> getListOfBooks(){
        return listOfBooks;
    }

    public Book getBookById(int id){
        return listOfBooks.stream()
                .filter(x->x.getId()==id).findFirst().get();

    }

    public List<Book> addBook(Book book){
         listOfBooks.add(book);
         return listOfBooks;
    }

public  Book updateBook(int id,Book book){
        return listOfBooks.stream()
                .filter(x->x.getId()==id)
                .peek(o->o.setTitle(book.getTitle()))
                .peek(o->o.setSubTitle(book.getSubTitle()))
                .peek(o->o.setAuthor(book.getAuthor()))
                 .peek(o->o.setPublishDate(book.getPublishDate()))
                .peek(o->o.setPages(book.getPages()))
                .peek(o->o.setDescription(book.getDescription()))
                .peek(o->o.setWebsite(book.getWebsite()))
                .findFirst().get();
}

public List<Book> deleteBook(int id){
        listOfBooks.removeIf(x->x.getId()==id);
        return listOfBooks;
}


}
