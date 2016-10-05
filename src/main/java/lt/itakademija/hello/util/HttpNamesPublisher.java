package lt.itakademija.hello.util;

import lt.itakademija.hello.InMemoryNamesRepository;
import lt.itakademija.hello.NamesRepository;

public final class HttpNamesPublisher {

	private final SimpleHttpServer httpServer;

	public HttpNamesPublisher(final int port, final NamesRepository namesRepository) {
		this.httpServer = new SimpleHttpServer(port, new NamesPublishingHttpRequestHandler("UTF-8", namesRepository));
	}

	public void publish() {
		this.httpServer.start();
	}

	private static final class NamesPublishingHttpRequestHandler extends SimpleHttpServer.HttpRequestHandlerBase {
		private final NamesRepository namesRepository;

		public NamesPublishingHttpRequestHandler(String charset, NamesRepository namesRepository) {
			super(charset);
			this.namesRepository = namesRepository;
		}

		@Override
		protected String getResponseBody() {
			StringBuilder sb = new StringBuilder();

			for (String name : namesRepository.getNames()) {
				sb.append(name).append("\n");
			}

			return sb.toString();
		}
	}

	public static void main(String[] args) {
		HttpNamesPublisher publisher = new HttpNamesPublisher(8081, new InMemoryNamesRepository());
		publisher.publish();
	}

}
