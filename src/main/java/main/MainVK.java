package main;

import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class MainVK {
	private final String PROPERTIES_FILE = "src/main/resources/app.properties";
	private String Application_ID;
	private String SECURE_KEY;
	private String SERVICE_TOKEN;
	private String BASE_DOMAIN;
	private String SCOPE;

	private String CODE;

	private String getAccessCodeURL;



	public static void main(String[] args) {
		MainVK main = new MainVK();
		main.getInitialConfig();
		// main.openBrowser("vk.com");
		main.getAccessCode();
	}

	public void getAccessCode() {
		// getAccessCodeURL =
		// "https://oauth.vk.com/authorize?scope=offline&redirect_uri=" +
		// BASE_DOMAIN + "&client_id="
		// + Application_ID + "&display=page";
		getAccessCodeURL = "https://oauth.vk.com/authorize?scope=" + SCOPE + "&client_id=" + Application_ID
				+ "&display=mobile";
		openBrowser(getAccessCodeURL);
		String codeUrl = "https://oauth.vk.com/blank.html#code=3b9f91e10144f1072e";
		CODE = getCode(codeUrl);
		System.out.println(CODE);
	}

	private String getCode(String url) {
		String regexp = "code=(.+)";

		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(url);
		return matcher.find() ? matcher.group(1) : null;

	}

	public void getInitialConfig() {
		getProperties();
	}

	private void getProperties() {
		/*
		 * "https://login.vk.com/?act=grant_access&client_id=5988698&settings=65536&redirect_uri=localhost&response_type=code&group_ids=&direct_hash=ba976a424518329ca3&token_type=0&v=&state=&display=page&ip_h=c608ae771490738c83&hash=9e0e7160344e25b739&https=1"
		 * +addr;
		 * https://oauth.vk.com/authorize?scope=offline&redirect_uri=localhost&
		 * client_id=5988698&display=mobile
		 * 
		 * https://oauth.vk.com/authorize?scope=notify,friends,photos,audio,
		 * video,pages,status,notes,wall,offline,groups,notifications,stats,
		 * email&client_id=5988698&display=mobile
		 * 
		 * https://oauth.vk.com/authorize?scope=notify,friends,photos,audio,
		 * video,pages,status,notes,messages,wall,offline,groups,notifications,
		 * stats,email&client_id=5988698&display=mobile
		 */
		Properties property = new Properties();

		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(PROPERTIES_FILE);
			property.load(inputStream);
			System.out.println("111");
			Application_ID = property.getProperty("application.Application_ID");
			SECURE_KEY = property.getProperty("application.SECURE_KEY");
			SERVICE_TOKEN = property.getProperty("application.SERVICE_TOKEN");
			BASE_DOMAIN = property.getProperty("application.BASE_DOMAIN");
			SCOPE = property.getProperty("application.SCOPE");
			System.out.println("111");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
			// TODO Auto-generated catch block

		}
	}

	private void openBrowser(String url) {
		Desktop desktop;
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				// launch browser
				URI uri;
				try {
					uri = new URI(url);
					desktop.browse(uri);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (URISyntaxException use) {
					use.printStackTrace();
				}
			}
		}
	}

	public String getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8000/api.v1/adverts/");

		String str = null;
		// String json = "{\"parser\": 4,\"xpath\": \"2\"}";
		StringEntity entity = new StringEntity(str, HTTP.UTF_8);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
		// httpPost.addHeader(
		// BasicScheme.authenticate(new UsernamePasswordCredentials("admin",
		// "iddqd"), "UTF-8", false));

		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println(EntityUtils.toString(response.getEntity()).replace(" ", "").replace("\n", ""));
		client.close();

		return null;
	}
}
