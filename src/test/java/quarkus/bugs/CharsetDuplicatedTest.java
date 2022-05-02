package quarkus.bugs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import javax.ws.rs.core.HttpHeaders;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class CharsetDuplicatedTest {
	@Test
	void testCorrectCharset() {
		given().when().body("{}").contentType(ContentType.JSON).post("/charset-duplicated").then()
			.statusCode(200)
			.header(HttpHeaders.CONTENT_TYPE, is("application/xml;charset=ISO-8859-1"));
	}
}
