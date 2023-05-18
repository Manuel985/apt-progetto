package com.drago.manuel.scommesse.view.swing;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.drago.manuel.scommesse.controller.EventController;

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
}
