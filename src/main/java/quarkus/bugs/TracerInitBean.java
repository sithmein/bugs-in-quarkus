package quarkus.bugs;

import io.opentelemetry.api.trace.Tracer;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class TracerInitBean {
	@Inject Tracer tracer;
	void startup(@Observes StartupEvent event) {
		tracer.spanBuilder("test").startSpan().end();
	}
}
