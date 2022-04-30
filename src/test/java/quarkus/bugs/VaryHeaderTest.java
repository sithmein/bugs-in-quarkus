package quarkus.bugs;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class VaryHeaderTest {
	@Test
	void testVaryHeaderValues() {
		var headers = given().header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN).when().get("/incomplete-vary-header")
				.then()
				.statusCode(200)
				.extract().headers();
		assertThat(headers.getValues(HttpHeaders.VARY)).as("Unexpected Vary header").contains("Origin", "Prefer");
	}
}
