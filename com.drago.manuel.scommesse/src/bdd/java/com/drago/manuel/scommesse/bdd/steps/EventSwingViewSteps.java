package com.drago.manuel.scommesse.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import javax.swing.JFrame;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.bson.Document;

import com.mongodb.MongoClient;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EventSwingViewSteps {

	private static final String DB_NAME = "bet";
	private static final String COLLECTION_NAME = "event";
	private MongoClient mongoClient;
	private FrameFixture window;

	@Before
	public void setUp() {
		mongoClient = new MongoClient();
		mongoClient.getDatabase(DB_NAME).drop();
	}

	@After
	public void tearDown() {
		mongoClient.close();
		if (window != null)
			window.cleanUp();
	}

	@Given("The database contains an event with home team {string} and away team {string} and outcome {string} and odds {string}")
	public void the_database_contains_an_event_with_home_team_and_away_team_and_outcome_and_odds(String string,
			String string2, String string3, String string4) {
		mongoClient.getDatabase(DB_NAME).getCollection(COLLECTION_NAME)
				.insertOne(new Document().append("homeTeam", string).append("awayTeam", string2)
						.append("outcome", string3).append("odds", Double.parseDouble(string4)));
	}

	@When("The Student View is shown")
	public void the_Student_View_is_shown() {
		application("com.drago.manuel.scommesse.app.swing.EventSwingApp")
				.withArgs("--db-name=" + DB_NAME, "--db-collection=" + COLLECTION_NAME).start();
		window = WindowFinder.findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
			@Override
			protected boolean isMatching(JFrame frame) {
				return "Event View".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(BasicRobot.robotWithCurrentAwtHierarchy());
	}

	@Then("The list contains an element with home team {string} and away team {string} and outcome {string} and odds {string}")
	public void the_list_contains_an_element_with_home_team_and_away_team_and_outcome_and_odds(String string,
			String string2, String string3, String string4) {
		assertThat(window.list().contents()).anySatisfy(e -> assertThat(e).contains(string, string2, string3, string4));
	}

}
