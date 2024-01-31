/**
 * 
 */
package my.portal.comm.impl.ahc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.entity.SerializableEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.portal.comm.Request;
import my.portal.comm.RequestMethods;
import my.portal.comm.SwingHttpClient;
import my.portal.common.Impl;
import my.portal.common.ResponseDto;

@Impl(SwingHttpClient.class)
public class ApacheHttpClient5 implements SwingHttpClient {

	private static Logger log = LoggerFactory.getLogger(ApacheHttpClient5.class);
	private static DefaultConfigurations defaults = new DefaultConfigurations();
	private static ExecutorService executorService =  Executors.newCachedThreadPool(new ThreadFactoryImpl());  

	private CloseableHttpClient getClosableConn(Request uyapReq) {

		return HttpClients.custom().setDefaultCookieStore(defaults.getCookieStore())
//				.setDefaultCredentialsProvider(defaults.getCredentialProvider())
				.setDefaultRequestConfig(defaults.getRequestConfig()).setConnectionManager(defaults.getConnManager())
				.setUserAgent("UYAP-AHC-5").build();
	}
	
	@Override
	public void sendAsynRequest(Request request) {
		
		executorService.execute( () -> {
			ResponseDto responseDto = sendSyncRequest(request);
			request.getResponseListener().responseCompleted(responseDto);
		});
		
	}

	@Override
	public ResponseDto sendSyncRequest(Request request) {

		try (CloseableHttpClient httpClient = getClosableConn(request)) {

			// create metot
			HttpUriRequest method = createMethod(request);

			// execute
			return httpClient.execute(method, this::handleResponse);
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private ResponseDto handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
		
		notifyHeaderListener(response.getHeaders());
		
		try (ObjectInputStream objectInputStream = new ObjectInputStream(response.getEntity().getContent())) {
			return (ResponseDto) objectInputStream.readObject();
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe.getMessage(), cnfe);
		}
	}

	private void notifyHeaderListener(Header[] headers) {
		//cookie managed by cookie store
		//so other headers just debugging with this method.
		if(log.isDebugEnabled()) {
			for(int i=0; i< headers.length; i++) {
				Header header = headers[i];
				log.debug("header-{} n:{}, value:{}",i, header.getName(), header.getValue());
			}
		}
		
	}


	private HttpUriRequest createMethod(Request request) throws URISyntaxException {

		URI uri = getUri(request);

		HttpUriRequest method;
		if (RequestMethods.GET.equals(request.getMethod())) {
			method = new HttpGet(uri);
		} else {
			method = new HttpPost(uri);
			// post method need some settings
			// check "application/octet-stream"
//			method.addHeader("Content-Type", "multipart/form-data");
			method.setEntity(new SerializableEntity(request.getRequestDto(), ContentType.MULTIPART_FORM_DATA));
		}

		// set Headers
		request.getHeaders().forEach(nv -> method.addHeader(nv.getName(), nv.getValue()));
		return method;
	}

	private URI getUri(Request request) throws URISyntaxException {
		URIBuilder builder = new URIBuilder(request.getUri());
		builder.addParameter("command", request.getRequestDto().getCommandName());
		request.getUriParams().forEach(nv -> builder.addParameter(nv.getName(), nv.getValue()));
		return builder.build();
	}


}
