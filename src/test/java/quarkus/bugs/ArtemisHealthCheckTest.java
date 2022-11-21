package quarkus.bugs;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ArtemisHealthCheckTest {
	@Test
	void testHealth() {
		given().when().get("/q/health").then().statusCode(200);
	}
}
