package com.drago.manuel.scommesse.view.swing;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

import javax.swing.JFrame;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.bson.Document;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.testcontainers.containers.MongoDBContainer;

import com.mongodb.MongoClient;

@RunWith(GUITestRunner.class)
public class EventSwingAppE2E extends AssertJSwingJUnitTestCase {

	@ClassRule
	public static final MongoDBContainer mongo = new MongoDBContainer("mongo:4.4.3");

	private static final String DB_NAME = "bet";
	private static final String COLLECTION_NAME = "event";

	private static final String EVENT_FIXTURE_1_HOME_TEAM = "Juventus";
	private static final String EVENT_FIXTURE_1_AWAY_TEAM = "Inter";
	private static final String EVENT_FIXTURE_1_OUTCOME = "1";
	private static final double EVENT_FIXTURE_1_ODDS = 2.15;
	private static final String EVENT_FIXTURE_2_HOME_TEAM = "Roma";
	private static final String EVENT_FIXTURE_2_AWAY_TEAM = "Lazio";
	private static final String EVENT_FIXTURE_2_OUTCOME = "2";
	private static final double EVENT_FIXTURE_2_ODDS = 2.35;

	private MongoClient mongoClient;

	private FrameFixture window;

	@Override
	protected void onSetUp() {
		String containerIpAddress = mongo.getHost();
		Integer mappedPort = mongo.getFirstMappedPort();
		mongoClient = new MongoClient(containerIpAddress, mappedPort);
		mongoClient.getDatabase(DB_NAME).drop();
		addTestEventToDatabase(EVENT_FIXTURE_1_HOME_TEAM, EVENT_FIXTURE_1_AWAY_TEAM, EVENT_FIXTURE_1_OUTCOME,
				EVENT_FIXTURE_1_ODDS);
		addTestEventToDatabase(EVENT_FIXTURE_2_HOME_TEAM, EVENT_FIXTURE_2_AWAY_TEAM, EVENT_FIXTURE_2_OUTCOME,
				EVENT_FIXTURE_2_ODDS);
		application("com.drago.manuel.scommesse.app.swing.EventSwingApp").withArgs("--mongo-host=" + containerIpAddress,
				"--mongo-port=" + mappedPort.toString(), "--db-name=" + DB_NAME, "--db-collection=" + COLLECTION_NAME)
				.start();
		window = WindowFinder.findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {
			@Override
			protected boolean isMatching(JFrame frame) {
				return "Event View".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(robot());
	}

	private void addTestEventToDatabase(String eventFixtureHomeTeam, String eventFixtureAwayTeam,
			String eventFixtureOutcome, double eventFixtureOdds) {
		mongoClient.getDatabase(DB_NAME).getCollection(COLLECTION_NAME).insertOne(
				new Document().append("homeTeam", eventFixtureHomeTeam).append("awayTeam", eventFixtureAwayTeam)
						.append("outcome", eventFixtureOutcome).append("odds", eventFixtureOdds));

	}

	@Override
	protected void onTearDown() {
		mongoClient.close();
	}

}
