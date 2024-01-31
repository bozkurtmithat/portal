package my.portal.comm.impl.ahc;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.config.TlsConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

public class DefaultConfigurations {

	private CookieStore cookieStore;
	private CredentialsProvider credentialProvider;
	private RequestConfig requestConfig;
	private ConnectionConfig connectionConfig;
	private TlsConfig tlsConfig;
	private SocketConfig socketConfig;
	private HttpClientConnectionManager connManager;

	public DefaultConfigurations() {
		cookieStore = new BasicCookieStore();

		requestConfig = RequestConfig.custom().setCircularRedirectsAllowed(false)
				.setConnectionRequestTimeout(Timeout.ofSeconds(5L)).setHardCancellationEnabled(true)
				.setContentCompressionEnabled(true).setConnectionKeepAlive(TimeValue.ofSeconds(10))
				.setDefaultKeepAlive(10, TimeUnit.SECONDS).setRedirectsEnabled(true)
				.setResponseTimeout(120, TimeUnit.SECONDS).build();

		connectionConfig = ConnectionConfig.custom().setConnectTimeout(Timeout.ofSeconds(3L))
				.setSocketTimeout(Timeout.ofSeconds(120)).setTimeToLive(TimeValue.ofMinutes(30)).build();

		socketConfig = SocketConfig.custom().setBacklogSize(0) // system default
				.setRcvBufSize(0) // system default
				.setSndBufSize(0) // system default
				// Specify a linger-on-close timeout. This option disables/enables immediate
				// return from a close() of a TCP Socket. Enabling this option with a non-zero
				// Integer timeout means that a close() will block pending the transmission and
				// acknowledgement of all data written to the peer, at which point the socket is
				// closed gracefully. Upon reaching the linger timeout, the socket is closed
				// forcefully, with a TCP RST. Enabling the option with a timeout of zero does a
				// forceful close immediately. If the specified timeout value exceeds 65,535 it
				// will be reduced to 65,535.
				.setSoLinger(TimeValue.ofSeconds(3))
				//Determines the default socket timeout value for blocking I/O operations.
				.setSoTimeout(Timeout.ofSeconds(120))
				.setSoKeepAlive(true)
				.setSoReuseAddress(true)
				.build();

		tlsConfig = TlsConfig.custom()
				.setHandshakeTimeout(Timeout.ofSeconds(3))
				.setSupportedProtocols(TLS.V_1_2, TLS.V_1_3)
				.setVersionPolicy(HttpVersionPolicy.NEGOTIATE)
				.build();

		BasicHttpClientConnectionManager bhcm = new BasicHttpClientConnectionManager();
		bhcm.setConnectionConfig(connectionConfig);
		bhcm.setSocketConfig(socketConfig);
		bhcm.setTlsConfig(tlsConfig);
		connManager = bhcm;
	}
	
	public HttpClientConnectionManager getConnManager() {
		return connManager;
	}
	
	public CookieStore getCookieStore() {
		return cookieStore;
	}
	
	public ConnectionConfig getConnectionConfig() {
		return connectionConfig;
	}
	
	public RequestConfig getRequestConfig() {
		return requestConfig;
	}
	
	public CredentialsProvider getCredentialProvider() {
		return credentialProvider;
	}
	
	public TlsConfig getTlsConfig() {
		return tlsConfig;
	}
	
	public SocketConfig getSocketConfig() {
		return socketConfig;
	}
}
