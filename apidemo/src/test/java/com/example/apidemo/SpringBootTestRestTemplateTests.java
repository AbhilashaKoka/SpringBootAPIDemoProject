package com.example.apidemo;
import com.example.apidemo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Instant;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootTestRestTemplateTests {

	@Autowired
    TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void testGetBookById(){
    //Arrange
    final String baseUrl="http://localhost:"+ port +"/Books/1";

	 var book=Book.builder()
			 .id(1)
			 .isbn( "9781449325862")
			 .title("Git Pocket Guide")
			 .subTitle("A Working Introduction")
			 .author("Richard E. Silverman")
			 .publishDate(Instant.parse("2020-06-04T08:48:39.000Z"))
			 .publisher("O'Reilly Media")
			 .pages(234)
			 .description("This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp")
			 .website("http://chimera.labs.oreilly.com/books/1230000000561/index.html").build();

	 //Act
		var responseEntity=this.restTemplate.getForObject(baseUrl, Book.class);

       //Assert
        assertThat(responseEntity).isEqualTo(book);
}

    @Test
	public void testGetBooks() {
		final String baseUrl = "http://localhost:" + port + "/Books";
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
		var responseEntity = this.restTemplate.getForObject(baseUrl, Book[].class);

		var responseBook= Arrays.stream(responseEntity).filter(x->x.getId()==1).findFirst().get();

		//Assert
		assertThat(responseBook).isEqualTo(book);
	}

}
