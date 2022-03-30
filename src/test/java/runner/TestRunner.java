package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import utils.SeleniumInitializer;

@CucumberOptions(
        features = "src/test/resources/features/Wishlist.feature",
        glue = "stepdefs",
        tags = "@Test",
        dryRun = false,
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-report",
                "junit:target/cucumber.xml",
                "rerun:target/rerun.txt"
        }
)
public class TestRunner extends SeleniumInitializer {

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
