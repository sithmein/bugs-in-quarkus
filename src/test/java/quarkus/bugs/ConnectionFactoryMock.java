package quarkus.bugs;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
public class ConnectionFactoryMock implements ConnectionFactory {
    @Override
    public Connection createConnection() throws JMSException {
        return null; // this should cause an error in the test
    }

    @Override
    public Connection createConnection(final String userName, final String password)
        throws JMSException {
    	return null; // the health check does no care about the returned value
    }

    @Override
    public JMSContext createContext() {
    	return null; // the health check does no care about the returned value
    }

    @Override
    public JMSContext createContext(final int sessionMode) {
    	return null; // the health check does no care about the returned value
    }

    @Override
    public JMSContext createContext(final String userName, final String password) {
    	return null; // the health check does no care about the returned value
    }

    @Override
    public JMSContext createContext(final String userName, final String password,
        final int sessionMode) {
    	return null; // the health check does no care about the returned value
    }
}
