package quarkus.bugs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class RoutingBrokenTest {
	@Test
	void testRouting() {
		given().when().get("/routing-broken/123/some/path").then()
			.statusCode(200)
			.body(is("123"));
        given().when().get("/routing-broken/456/some/other/path").then()
	        .statusCode(200)
	        .body(is("456"));
	}
}
