package quarkus.bugs;

import org.junit.jupiter.api.Test;

import io.opentelemetry.api.OpenTelemetry;
import io.quarkus.arc.Arc;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class OpenTelemetryInitTest {
	@Test
	void testInit() {
		Arc.container().instance(OpenTelemetry.class).get();
	}
}
