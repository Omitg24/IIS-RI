package uo.ri.cws.application.service;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)


@CucumberOptions(
		features = {
		"src/test/resources/uo/ri/cws/application/service/mechanic"
		//, "src/test/resources/uo/ri/cws/application/service/contract"
		,"src/test/resources/uo/ri/cws/application/service/payroll"
		//, "src/test/resources/uo/ri/cws/application/service/professionalgroup"
		, "src/test/resources/uo/ri/cws/application/service/contracttype"
		}		
		)

public class TestRunner {

}
