package quarkus.bugs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class MediaTypeLostTest {
	@Test
	void testMediaTypeInWriter() {
		given().when().get("/media-type-lost").then()
			.statusCode(200)
			.header("Copy-Of-Content-Type", is(MediaType.APPLICATION_JSON));
	}
}
