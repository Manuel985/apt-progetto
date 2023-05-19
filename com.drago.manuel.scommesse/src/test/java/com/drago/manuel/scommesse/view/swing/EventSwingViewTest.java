package com.drago.manuel.scommesse.view.swing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.model.EventModel;

@RunWith(GUITestRunner.class)
public class EventSwingViewTest extends AssertJSwingJUnitTestCase {

	private FrameFixture window;

	private EventSwingView eventSwingView;

	@Mock
	private EventController eventController;

	@Override
	protected void onSetUp() {
		MockitoAnnotations.openMocks(this);
		GuiActionRunner.execute(() -> {
			eventSwingView = new EventSwingView();
			eventSwingView.setEventController(eventController);
			return eventSwingView;
		});
		window = new FrameFixture(robot(), eventSwingView);
		window.show();
	}

	@Test
	@GUITest
	public void testControlsInitialStates() {
		window.label(JLabelMatcher.withText("Home team"));
		window.textBox("homeTeamTextBox").requireEnabled();
		window.label(JLabelMatcher.withText("Away team"));
		window.textBox("awayTeamTextBox").requireEnabled();
		window.label(JLabelMatcher.withText("Outcome"));
		window.textBox("outcomeTextBox").requireEnabled();
		window.label(JLabelMatcher.withText("Odds"));
		window.textBox("oddsTextBox").requireEnabled();
		window.button(JButtonMatcher.withText("Add")).requireDisabled();
		window.list("eventList");
		window.button(JButtonMatcher.withText("Delete")).requireDisabled();
		window.button(JButtonMatcher.withText("Change Odds")).requireDisabled();
		window.label("errorMessageLabel").requireText(" ");
	}

	@Test
	public void testWhenHomeAwayOutcomeOddsAreNonEmptyThenAddButtonShouldBeEnabled() {
		window.textBox("homeTeamTextBox").enterText("Juventus");
		window.textBox("awayTeamTextBox").enterText("Inter");
		window.textBox("outcomeTextBox").enterText("X");
		window.textBox("oddsTextBox").enterText("3.20");
		window.button(JButtonMatcher.withText("Add")).requireEnabled();
	}

	@Test
	public void testWhenHomeOrAwayOrOutcomeOrOddsAreBlankThenAddButtonShouldBeDisabled() {
		JTextComponentFixture homeTeamTextBox = window.textBox("homeTeamTextBox");
		JTextComponentFixture awayTeamTextBox = window.textBox("awayTeamTextBox");
		JTextComponentFixture outcomeTextBox = window.textBox("outcomeTextBox");
		JTextComponentFixture oddsTextBox = window.textBox("oddsTextBox");

		homeTeamTextBox.enterText("Juventus");
		awayTeamTextBox.enterText("Inter");
		outcomeTextBox.enterText("1");
		oddsTextBox.enterText(" ");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();

		homeTeamTextBox.setText("");
		awayTeamTextBox.setText("");
		outcomeTextBox.setText("");
		oddsTextBox.setText("");

		homeTeamTextBox.enterText("Juventus");
		awayTeamTextBox.enterText("Inter");
		outcomeTextBox.enterText(" ");
		oddsTextBox.enterText("2.80");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();

		homeTeamTextBox.setText("");
		awayTeamTextBox.setText("");
		outcomeTextBox.setText("");
		oddsTextBox.setText("");

		homeTeamTextBox.enterText("Juventus");
		awayTeamTextBox.enterText(" ");
		outcomeTextBox.enterText("X");
		oddsTextBox.enterText("2.80");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();

		homeTeamTextBox.setText("");
		awayTeamTextBox.setText("");
		outcomeTextBox.setText("");
		oddsTextBox.setText("");

		homeTeamTextBox.enterText(" ");
		awayTeamTextBox.enterText("Inter");
		outcomeTextBox.enterText("X");
		oddsTextBox.enterText("2.80");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();
	}

	@Test
	public void testDeleteButtonShouldBeEnabledOnlyWhenAnEventIsSelected() {
		GuiActionRunner.execute(
				() -> eventSwingView.getListEventsModel().addElement(new EventModel("Juventus", "Inter", "X", 3.20)));
		window.list("eventList").selectItem(0);
		JButtonFixture deleteButton = window.button(JButtonMatcher.withText("Delete"));
		deleteButton.requireEnabled();
		window.list("eventList").clearSelection();
		deleteButton.requireDisabled();
	}

	@Test
	public void testsShowAllEventsShouldAddEventDescriptionsToTheList() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.25);
		EventModel eventModel2 = new EventModel("Roma", "Lazio", "X", 3.65);
		GuiActionRunner.execute(() -> eventSwingView.showAllEvents(Arrays.asList(eventModel, eventModel2)));
		String[] listContents = window.list().contents();
		assertThat(listContents).containsExactly("Juventus - Inter = X - 3.25", "Roma - Lazio = X - 3.65");
	}

	@Test
	public void testShowErrorShouldShowTheMessageInTheErrorLabel() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.25);
		GuiActionRunner.execute(() -> eventSwingView.showError("error message", eventModel));
		window.label("errorMessageLabel").requireText("error message: Juventus - Inter = X - 3.25");
	}
}
