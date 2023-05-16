package com.drago.manuel.scommesse.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
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
	public void testNewEventWhenEventDoesNotAlreadyExist() {
		String homeTeam = "Juventus";
		String awayTeam = "Inter";
		String outcome = "X";
		double odds = 3.20;
		EventModel eventModel = new EventModel(homeTeam, awayTeam, outcome, odds);
		when(eventRepository.findByHomeAwayOutcome(homeTeam, awayTeam, outcome)).thenReturn(null);
		eventController.newEvent(eventModel);
		InOrder inOrder = inOrder(eventRepository, eventView);
		inOrder.verify(eventRepository).save(eventModel);
		inOrder.verify(eventView).eventAdded(eventModel);
	}

}
