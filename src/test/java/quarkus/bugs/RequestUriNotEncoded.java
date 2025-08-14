package quarkus.bugs;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.client.ClientBuilder;

@QuarkusTest
class RequestUriNotEncoded {
	private static final WireMockServer wireMock = new WireMockServer(wireMockConfig().dynamicPort());
	
	private static final String ENCODED_PATH = "/with%20space"; 
	private static final String DECODED_PATH = "/with space";
	
	@BeforeAll
	static void startWiremock() {
        wireMock.start();
        WireMock.configureFor(wireMock.port());
        stubFor(get(urlEqualTo(ENCODED_PATH)).willReturn(noContent()));
        stubFor(get(urlEqualTo(DECODED_PATH)).willReturn(noContent()));
	}
	
	@Test
	void testEncodedPath() throws Exception {
        var request = ClientBuilder.newClient().target(wireMock.baseUrl() + ENCODED_PATH).request();
        try (var response = request.get()) {
			assertThat(response.getStatus())
					.as("Unexpected HTTP status with message %s", response.readEntity(String.class)).isEqualTo(204);
        }
	}
	
	@Test
	void testDecodedPath() throws Exception {
        var request = ClientBuilder.newClient().target(wireMock.baseUrl() + DECODED_PATH).request();
        try (var response = request.get()) {
			assertThat(response.getStatus())
					.as("Unexpected HTTP status with message %s", response.readEntity(String.class)).isEqualTo(204);
        }
	}
}
