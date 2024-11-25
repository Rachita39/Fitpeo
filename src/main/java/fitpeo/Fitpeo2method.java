package fitpeo;


import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fitpeo2method {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver (Chrome in this case)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Test Case 1: Navigate to the FitPeo Homepage
            driver.get("https://www.fitpeo.com"); // URL for FitPeo homepage

            // Test Case 2: Navigate to the Revenue Calculator Page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Correct usage of WebDriverWait
            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/header/div/div[3]/div[6]/a/div")));
            revenueCalculatorLink.click();

            // Test Case 3: Scroll Down to the Slider Section
            WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[1]"))); // Replace with actual XPath to the slider section

            // Scroll to the slider section using JavaScript Executor
            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection); // Scroll until the slider section is in view
//            js.executeScript("window.scrollBy(900,649)");

            // Test Case 4: Adjust the Slider
//            x= 526.4000244140625 y = 327.2250061035156
            WebElement element = driver.findElement(By.xpath("//div[@class='MuiBox-root css-j7qwjs']//span[1]"));
            Point point = element.getLocation();
            System.out.println("Element's Position from left side is: "+point.getX()+" pixels.");
            System.out.println("Element's Position from top is: "+point.getY()+" pixels.");
            WebElement slider = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[2]")); // XPath to the slider element
            Actions actions = new Actions(driver);
            
            //to get 823
//            int xoffset = 53;
//            WebElement sliderTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div/input"))); // XPath to the text field
//            String updatedValue = sliderTextField.getAttribute("value");
//            System.out.println("Updated Slider Value: " + updatedValue);
//            for(int i=xoffset;i<1000;i++) {
//            	updatedValue = sliderTextField.getAttribute("value");
//            	actions.clickAndHold(slider).moveByOffset(xoffset , 0).release().perform();
//            	updatedValue = sliderTextField.getAttribute("value");
//            	int value = Integer.parseInt(updatedValue);
//            	if(value==816)
//            		break;
//            	else
//            		xoffset++;
//            }
            actions.clickAndHold(slider).moveByOffset(108 , 0).release().perform();// Move the slider to a new position
//            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            // Wait for the text field value to update (optional)
            WebElement sliderTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-79elbk']//div[@class='MuiFormControl-root MuiTextField-root css-1s5tg4z']//input"))); // XPath to the text field
            String updatedValue = sliderTextField.getAttribute("value");
            System.out.println("Updated Slider Value: " + updatedValue);
            assert updatedValue.equals("823"); // Validate slider value updates to 823

            // Test Case 5: Update the Text Field
            sliderTextField.click();
            sliderTextField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            sliderTextField.sendKeys("560");

            // Test Case 6: Validate Slider Value after Text Field Update
            WebElement updatedSlider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='range']"))); // XPath to the slider
            String updatedSliderPosition = updatedSlider.getAttribute("value"); // Assuming the slider value is accessible this way
            System.out.println("Slider value after text field update: " + updatedSliderPosition);
            assert updatedSliderPosition.equals("560"); // Validate the slider moves to 560 after text field update

            // Test Case 7: Select CPT Codes
            WebElement cpt99091 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox'])[1]")); // XPath to CPT-99091 checkbox
            WebElement cpt99453 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox'])[2]"));
            WebElement cpt99454 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox'])[3]"));
            WebElement cpt99474 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox'])[8]"));

            cpt99091.click();
            cpt99453.click();
            cpt99454.click();
            cpt99474.click();

            // Test Case 8: Validate Total Recurring Reimbursement
            WebElement totalRecurringReimbursement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]"))); // XPath to the total recurring reimbursement
            String totalReimbursementValue = totalRecurringReimbursement.getText();
            System.out.println("Total Recurring Reimbursement: " + totalReimbursementValue);
            System.out.println();
            Assertions.assertTrue(totalReimbursementValue.equals("$75600"));
            Assertions.assertFalse(totalReimbursementValue.equals("$7560"));
            assert totalReimbursementValue.equals("$110700"); // Validate that the total is correct

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser window
            driver.quit();
        }
		
		
		
	}

}
