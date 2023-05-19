package com.drago.manuel.scommesse.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.EventRepository;
import com.drago.manuel.scommesse.view.EventView;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

	@InjectMocks
	private EventController eventController;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private EventView eventView;

	@Test
	public void testAllEvents() {
		List<EventModel> eventModels = asList(new EventModel());
		when(eventRepository.findAll()).thenReturn(eventModels);
		eventController.allEvents();
		verify(eventView).showAllEvents(eventModels);
	}

	@Test
	public void testNewEventWhenEventDoesNotAlreadyExistAndOddsIsGreaterThenOne() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		when(eventRepository.findByHomeAwayOutcome("Juventus", "Inter", "X")).thenReturn(null);
		eventController.newEvent(eventModel);
		InOrder inOrder = inOrder(eventRepository, eventView);
		inOrder.verify(eventRepository).save(eventModel);
		inOrder.verify(eventView).eventAdded(eventModel);
	}

	@Test
	public void testNewEventWhenOddsIsNotGreaterThenOne() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 1.0);
		eventController.newEvent(eventModel);
		verify(eventView).showError("Odds must be greater then 1.0", eventModel);
		verify(eventRepository, never()).save(eventModel);

	}

	@Test
	public void testNewEventWhenEventAlreadyExist() {
		EventModel newEventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		EventModel existingEventModel = new EventModel("Juventus", "Inter", "X", 2.20);
		when(eventRepository.findByHomeAwayOutcome("Juventus", "Inter", "X")).thenReturn(existingEventModel);
		eventController.newEvent(newEventModel);
		verify(eventView).showError("Already existing event Juventus-Inter X", existingEventModel);
		verifyNoMoreInteractions(ignoreStubs(eventRepository));
	}

	@Test
	public void testDeleteEventWhenEventExists() {
		EventModel eventModelToDelete = new EventModel("Juventus", "Inter", "X", 3.20);
		eventController.deleteEvent(eventModelToDelete);
		InOrder inOrder = inOrder(eventRepository, eventView);
		inOrder.verify(eventRepository).delete("Juventus", "Inter", "X");
		inOrder.verify(eventView).eventRemoved(eventModelToDelete);
	}

	@Test
	public void testChangeEventWhenOddsIsGreaterThenOne() {
		EventModel existingEventModel = new EventModel("Juventus", "Inter", "X", 2.20);
		EventModel modifiedEventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		eventController.changeOdds(existingEventModel, modifiedEventModel.getOdds());
		InOrder inOrder = inOrder(eventRepository, eventView);
		inOrder.verify(eventRepository).update(modifiedEventModel);
		inOrder.verify(eventView).eventChanged(existingEventModel, modifiedEventModel.getOdds());

	}

	@Test
	public void testChangeEventWhenOddsIsNotGreaterThenOne() {
		EventModel existingEventModel = new EventModel("Juventus", "Inter", "X", 2.20);
		EventModel modifiedEventModel = new EventModel("Juventus", "Inter", "X", 1.0);
		eventController.changeOdds(existingEventModel, modifiedEventModel.getOdds());
		verify(eventView).showError("Odds must be greater then 1.0", modifiedEventModel);
		verify(eventRepository, never()).update(modifiedEventModel);

	}

}
