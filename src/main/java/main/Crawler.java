package main;

import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Crawler {

	public static void main(String[] args) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);

		if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					"/easyvkclient/phantomjs/phantomjs-2.1.1-windows/phantomjs.exe");

			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					"C:/Users/Sergey/Pictures/phantomjs/phantomjs-2.1.1-windows/phantomjs.exe");

		}

		System.out.println(System.getProperties().getProperty("os.name"));
		WebDriver driver = new PhantomJSDriver(caps);
		System.out.println("ok");
		driver.get("https://oauth.vk.com/authorize?scope=notify,friends,photos,audio,video,pages,status,notes,messages,wall,offline,groups,notifications,stats,email&client_id=5988698&display=mobile");
		System.out.println(driver.getTitle());
		WebElement anotherElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("another-element")));
		driver.findElement(By.cssSelector("input.button")).click();
		
		System.out.println(driver.getCurrentUrl());
	}
}
