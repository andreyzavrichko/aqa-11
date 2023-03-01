package com.example.aqa;

import com.example.aqa.models.CustomObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AqaApplicationTests {

	RequestSpecification request;

	@BeforeAll
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080";
	}

	@BeforeEach
	public void setRequest() {
		request = RestAssured.given();
	}

	@Test
	void contextLoads() {

	}

	@Test
	void shouldGetDefaultString() {
		Response response = request.get("/getString");

		assertThat(response)
				.extracting(
						Response::getContentType,
						Response::getStatusCode,
						Response::getStatusLine
				).containsExactly(
						"text/plain;charset=UTF-8",
						200,
						"HTTP/1.1 200 "
				);

		assertThat(response.body().print()).isEqualTo("empty string");
	}

	@Test
	void shouldGetStringWithoutSpaces() {
		final String str = " some string ";
		Response response = request
				.param("str", str)
				.get("/getString");

		assertThat(response)
				.extracting(
						Response::getContentType,
						Response::getStatusCode
				).containsExactly(
						"text/plain;charset=UTF-8",
						200
				);

		assertThat(response.body().print()).isEqualTo(str.trim());
	}

	@Test
	public void shouldGetObject() {
		Response response = request.get("/getObject");

		assertThat(response)
				.extracting(
						Response::getContentType,
						Response::getStatusCode
				).containsExactly(
						"application/json",
						200
				);

		final var body = response.body().peek().as(CustomObject.class);
		assertThat(body.page).isEqualTo(0);
		assertThat(body.query).isEqualTo("куртка");
		assertThat(body.size).isEqualTo(10);
	}
}
