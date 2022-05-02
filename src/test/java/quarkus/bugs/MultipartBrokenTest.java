package quarkus.bugs;

import static io.restassured.RestAssured.given;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.MultiPartSpecBuilder;

@QuarkusTest
class MultipartBrokenTest {
	@Test
	void testCorrectCharset() throws Exception {
		var part1 = new MultiPartSpecBuilder("12345".getBytes(StandardCharsets.UTF_8)).controlName("id1")
				.fileName("id1").mimeType("multipart/form-data").build();

        given().when().multiPart(part1).contentType("multipart/form-data")
            .post("/multipart-broken")
            .then().statusCode(200);
	}
}
