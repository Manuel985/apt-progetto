package com.drago.manuel.scommesse.view.swing;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetSocketAddress;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.mongo.EventMongoRepository;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

@RunWith(GUITestRunner.class)
public class EventSwingViewIT extends AssertJSwingJUnitTestCase {

	private static MongoServer server;
	private static InetSocketAddress serverAddress;

	private MongoClient mongoClient;

	private FrameFixture window;
	private EventSwingView eventSwingView;
	private EventController eventController;
	private EventMongoRepository eventMongoRepository;

	private static final String BET_DB_NAME = "bet";
	private static final String EVENT_COLLECTION_NAME = "event";

	@BeforeClass
	public static void setupServer() {
		server = new MongoServer(new MemoryBackend());
		serverAddress = server.bind();
	}

	@AfterClass
	public static void shutdownServer() {
		server.shutdown();
	}

	@Override
	protected void onSetUp() {
		mongoClient = new MongoClient(new ServerAddress(serverAddress));
		eventMongoRepository = new EventMongoRepository(mongoClient, BET_DB_NAME, EVENT_COLLECTION_NAME);
		for (EventModel eventModel : eventMongoRepository.findAll()) {
			eventMongoRepository.delete(eventModel.getHomeTeam(), eventModel.getAwayTeam(), eventModel.getOutcome());
		}
		GuiActionRunner.execute(() -> {
			eventSwingView = new EventSwingView();
			eventController = new EventController(eventMongoRepository, eventSwingView);
			eventSwingView.setEventController(eventController);
			return eventSwingView;
		});
		window = new FrameFixture(robot(), eventSwingView);
		window.show();
	}

	@Override
	protected void onTearDown() {
		mongoClient.close();
	}

	@Test
	@GUITest
	public void testAllEvents() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 2.85);
		EventModel eventModel2 = new EventModel("Roma", "Lazio", "X", 3.55);
		eventMongoRepository.save(eventModel);
		eventMongoRepository.save(eventModel2);
		GuiActionRunner.execute(() -> eventController.allEvents());
		assertThat(window.list().contents()).containsExactly("Juventus - Inter = X - 2.85", "Roma - Lazio = X - 3.55");
	}

	@Test
	@GUITest
	public void testAddButtonSuccess() {
		window.textBox("homeTeamTextBox").enterText("Juventus");
		window.textBox("awayTeamTextBox").enterText("Inter");
		window.textBox("outcomeTextBox").enterText("X");
		window.textBox("oddsTextBox").enterText("2.85");
		window.button(JButtonMatcher.withText("Add")).click();
		assertThat(window.list().contents()).containsExactly("Juventus - Inter = X - 2.85");
	}

	@Test
	@GUITest
	public void testAddButtonError() {
		eventMongoRepository.save(new EventModel("Juventus", "Inter", "X", 2.85));
		window.textBox("homeTeamTextBox").enterText("Juventus");
		window.textBox("awayTeamTextBox").enterText("Inter");
		window.textBox("outcomeTextBox").enterText("X");
		window.textBox("oddsTextBox").enterText("2.85");
		window.button(JButtonMatcher.withText("Add")).click();
		assertThat(window.list().contents()).isEmpty();
		window.label("errorMessageLabel").requireText("Already existing event Juventus-Inter X");
	}

	@Test
	@GUITest
	public void testDeleteButtonSuccess() {
		GuiActionRunner.execute(() -> eventController.newEvent(new EventModel("Juventus", "Inter", "X", 2.85)));
		window.list().selectItem(0);
		window.button(JButtonMatcher.withText("Delete")).click();
		assertThat(window.list().contents()).isEmpty();
	}

}
