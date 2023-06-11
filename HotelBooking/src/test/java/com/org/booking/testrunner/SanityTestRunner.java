package com.org.booking.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",glue="com.org.booking.stepdefs",tags="@sanity",
plugin = {
		"pretty",
		"html:target/cucumber",
		"json:target/cucumber-report/cucumber.json",
		"json:target/cucumber.json",
		"html: test-output/sanityReport.html"
		},monochrome=true)
public class SanityTestRunner {
	

}
