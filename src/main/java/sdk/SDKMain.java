package sdk;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;

public class SDKMain {
	private final String PROPERTIES_FILE = "src/main/resources/app.properties";
	private String Application_ID;
	private String SECURE_KEY;
	private String SERVICE_TOKEN;
	private String BASE_DOMAIN;
	private String SCOPE;

	private String CODE;

	private String getAccessCodeURL;

	public SDKMain() {
		getProperties();
		this.CODE = "4fbbb47e96e6b2fa58";
		// TODO Auto-generated method stub
		TransportClient transportClient = HttpTransportClient.getInstance();
		VkApiClient vk = new VkApiClient(transportClient, new Gson());
		UserAuthResponse authResponse = null;
		try {
			authResponse = vk.oauth()
					.userAuthorizationCodeFlow(Integer.parseInt(Application_ID), SECURE_KEY, "", CODE).execute();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
		System.out.println(actor.getAccessToken());
	}

	public static void main(String[] args) {
		SDKMain main = new SDKMain();
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

}
