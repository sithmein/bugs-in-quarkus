package quarkus.bugs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import javax.ws.rs.core.HttpHeaders;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class UnexpectedCharsetTest {
	@Test
	void testCorrectCharset() {
		given().when().body("{}").contentType(ContentType.JSON).accept("image/jpeg").post("/unexpected-charset").then()
			.statusCode(200)
			.header(HttpHeaders.CONTENT_TYPE, is("image/jpeg"));
		given().when().body("{}").contentType(ContentType.JSON).accept("image/png").post("/unexpected-charset").then()
			.statusCode(200)
			.header(HttpHeaders.CONTENT_TYPE, is("image/png"));
	}
}
