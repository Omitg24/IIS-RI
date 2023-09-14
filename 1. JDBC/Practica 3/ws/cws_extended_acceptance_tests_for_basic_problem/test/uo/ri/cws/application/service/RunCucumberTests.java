package uo.ri.cws.application.service;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import uo.ri.conf.BusinessFactoryAdapter;
import uo.ri.conf.Factory;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {		"test/uo/ri/cws/application/service/mechanic",
				"test/uo/ri/cws/application/service/invoice",
		},
	plugin = {"pretty", "html:target/cucumber-results.html"},
	snippets = SnippetType.CAMELCASE
)
public class RunCucumberTests {

	static {
		Factory.service = new BusinessFactoryAdapter();
	}

}
