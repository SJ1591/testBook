package runnerClass;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format= {"pretty","html:target/report.html"},
		features="Features/test.feature"
		,glue={"stepDef"}
		)
public class RunnerTest {
	
	

}
