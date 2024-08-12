package com.example.apidemo;
import com.example.apidemo.controller.BookController;
import com.example.apidemo.model.Book;
import com.example.apidemo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@WebMvcTest(BookController.class)
public class MockMVCWithSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Test
    void testGetBookById() throws Exception {

        //Arrange
        var book = Book.builder()
                .id(1)
                .isbn("9781449325862")
                .title("Git Pocket Guide")
                .subTitle("A Working Introduction")
                .author("Richard E. Silverman")
                .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
                .publisher("O'Reilly Media")
                .pages(234)
                .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
                .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build();

        //act
         when(bookService.getBookById(1)).thenReturn(book);

        //Assert
       var result=  this.mockMvc.perform(get("/Books/1"))
               .andExpect(MockMvcResultMatchers.jsonPath("title").value("Git Pocket Guide"))
               .andExpect(status().isOk());

    }


@Test
    public void testGetBooks() throws Exception {
         List<Book> listOfBooks = new ArrayList<>(List.of(Book.builder()
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

        //act
        when(bookService.getListOfBooks()).thenReturn(listOfBooks);

        //Assert
        var result=  this.mockMvc.perform(get("/Books"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Git Pocket Guide"))
                .andExpect(status().isOk());

    }
}
