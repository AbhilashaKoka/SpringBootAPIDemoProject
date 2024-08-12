package com.example.apidemo;
import com.example.apidemo.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Instant;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTest {

    @Value("${base.url}")
    private String baseUrl;

    @LocalServerPort
    private  int port;


    @Test
    public void testGetBookByID()
    {
        given()
                .baseUri(baseUrl)
                .port(port)
                .basePath("/Books/1")
                .get()
                .then()
                .assertThat()
                .body("title", Matchers.equalTo("Git Pocket Guide"));
    }

    @Test
    public void testGetBooks() {
        //Arrange
                var book  = Book.builder()
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


        //Act
     var response=given()
             .baseUri(baseUrl)
             .port(port)
             .basePath("/Books/1")
             .get();
     var responseEntity=response
             .body()
             .as(Book.class);


        //Assert
        assertThat(responseEntity)
                .isEqualTo(book);
    }


    @Test
    public void testPostBooks() {
        //Arrange
        var book  = Book.builder()
                .id(4)
                .isbn("9781449325862")
                .title("Git Pocket Guide")
                .subTitle("A Working Introduction")
                .author("Richard E. Silverman")
                .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
                .publisher("O'Reilly Media")
                .pages(234)
                .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
                .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build();
        //Act
        var response=given()
                .baseUri(baseUrl)
                .port(port)
                .basePath("/Books")
                .contentType("application/json")
                .body(book)
                .post();

        var responseEntity=response
                .body()
                .as(Book[].class);

        var responseBook= Arrays.stream(responseEntity).filter(x->x.getId()==4).findFirst().get();

        //Assert
        assertThat(responseBook).isEqualTo(book);

    }



    @Test
    public void testPutBooks() throws JsonProcessingException{
        //Arrange
        var book  =  Book.builder()
                .id(2)
                .isbn( "9781449325868")
                .title("Git Pocket Guide")
                .subTitle("A Working Introduction")
                .author("Richard E. Silverman")
                .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
                .publisher("O'Reilly Media")
                .pages(234)
                .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
                .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build();

        //Act
        var response=given()
                .baseUri(baseUrl)
                .port(port)
                .basePath("/Books/2")
                .contentType("application/json")
                .body(book)
                .put();

        var responseEntity=response
                .body()
                .as(Book.class);



        //Assert
        assertThat(responseEntity).isEqualTo(book);

    }



}
