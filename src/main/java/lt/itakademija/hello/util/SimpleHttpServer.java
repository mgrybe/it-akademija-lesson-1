package lt.itakademija.hello.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SimpleHttpServer {

	private static final Executor executors = Executors.newCachedThreadPool();

	private final int port;

	private final HttpRequestHandler handler;

	public SimpleHttpServer(final int port, final HttpRequestHandler handler) {
		this.port = port;
		this.handler = handler;
	}

	public SimpleHttpServer(int i) {
		this(i, new HelloWorldHttpRequestHandler("UTF-8"));
	}

	public void start() {
		try {
			ServerSocket socket = new ServerSocket(port);

			while (true) {
				final Socket connection = socket.accept();
				Runnable task = new Runnable() {
					@Override
					public void run() {
						try {
							handler.handleRequest(connection.getInputStream(), connection.getOutputStream());
						} catch (Exception e) {
							throw new RuntimeException(e);
						} finally {
							try {
								connection.close();
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						}
					}
				};
				executors.execute(task);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public interface HttpRequestHandler {

		void handleRequest(InputStream is, OutputStream os);

	}

	private static class HelloWorldHttpRequestHandler extends HttpRequestHandlerBase {

		public HelloWorldHttpRequestHandler(final String charset) {
			super(charset);
		}
		
		@Override
		protected String getResponseBody() {
			return "<html><head><title>IT Akademijos HTTP serveris</title><body><h1>Labas, Pasauli!</h1></body></head></html>";
		}

	}

	public static abstract class HttpRequestHandlerBase implements HttpRequestHandler {
		private final String charset;

		public HttpRequestHandlerBase(final String charset) {
			this.charset = charset;
		}

		@Override
		public void handleRequest(InputStream is, OutputStream os) {
			try {
				PrintWriter writer = createWriter(os, charset);
				writer.println("HTTP/1.0 200");
				writer.println("Content-type: text/html;charset=" + charset);
				writer.println("Server-name: it-akademijos-http-serveris");

				String response = getResponseBody();
				byte[] responseBody = response.getBytes(charset);
				
				writer.println("Content-length: " + responseBody.length);
				writer.println("");
				writer.flush();

				os.write(responseBody);
				os.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		protected abstract String getResponseBody();

		protected PrintWriter createWriter(final OutputStream os, final String charset) throws IOException {
			return new PrintWriter(new OutputStreamWriter(os, charset));
		}

		protected BufferedReader createReader(final Socket connection, final String charset) throws IOException {
			return new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
		}
	}

	public static void main(String[] args) throws IOException {
		SimpleHttpServer httpServer = new SimpleHttpServer(8081);
		httpServer.start();
	}

}
