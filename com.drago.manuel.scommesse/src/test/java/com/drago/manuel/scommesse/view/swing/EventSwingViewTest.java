package com.drago.manuel.scommesse.view.swing;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
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
}
