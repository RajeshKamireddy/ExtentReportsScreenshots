package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Base {
static int i=0;
public static String takeScreenshotFilePath(String testName,WebDriver driver) throws IOException {
		
		TakesScreenshot shot = (TakesScreenshot) driver;
		File src = shot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		File dest = new File(destinationPath);
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();
	}

}
