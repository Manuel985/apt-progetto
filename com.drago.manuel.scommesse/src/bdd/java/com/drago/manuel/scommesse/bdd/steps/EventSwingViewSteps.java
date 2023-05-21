package com.drago.manuel.scommesse.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import javax.swing.JFrame;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.matcher.JButtonMatcher;
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

	private static final String ODDS = "2.15";
	private static final String OUTCOME = "X";
	private static final String AWAY_TEAM = "Inter";
	private static final String HOME_TEAM = "Juventus";
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

	@Given("The database contains an event")
	public void the_database_contains_an_event() {
		addTestEventToDatabase(HOME_TEAM, AWAY_TEAM, OUTCOME, ODDS);
	}

	@Given("The Event View is shown")
	public void the_Event_View_is_shown() {
		application("com.drago.manuel.scommesse.app.swing.EventSwingApp")
				.withArgs("--db-name=" + DB_NAME, "--db-collection=" + COLLECTION_NAME).start();
		window = WindowFinder.findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
			@Override
			protected boolean isMatching(JFrame frame) {
				return "Event View".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(BasicRobot.robotWithCurrentAwtHierarchy());
	}

	@Given("The user provides event data in the text fields")
	public void the_user_provides_event_data_in_the_text_fields() {
		window.textBox("homeTeamTextBox").enterText("Roma");
		window.textBox("awayTeamTextBox").enterText("Lazio");
		window.textBox("outcomeTextBox").enterText("1");
		window.textBox("oddsTextBox").enterText("1.65");
	}

	@When("The user clicks the {string} button")
	public void the_user_clicks_the_button(String string) {
		window.button(JButtonMatcher.withText(string)).click();
	}

	@Then("The list contains the new event")
	public void the_list_contains_the_new_event() {
		assertThat(window.list().contents()).anySatisfy(e -> assertThat(e).contains("Roma", "Lazio", "1", "1.65"));
	}

	private void addTestEventToDatabase(String homeTeam, String awayTeam, String outcome, String odds) {
		mongoClient.getDatabase(DB_NAME).getCollection(COLLECTION_NAME)
				.insertOne(new Document().append("homeTeam", homeTeam).append("awayTeam", awayTeam)
						.append("outcome", outcome).append("odds", Double.parseDouble(odds)));
	}

}
