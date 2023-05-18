package com.drago.manuel.scommesse.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.EventRepository;
import com.drago.manuel.scommesse.repository.mongo.EventMongoRepository;
import com.drago.manuel.scommesse.view.EventView;
import com.mongodb.MongoClient;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerIT {

	@Mock
	private EventView eventView;

	private EventRepository eventRepository;

	private EventController eventController;

	private static final String BET_DB_NAME = "bet";
	private static final String EVENT_COLLECTION_NAME = "event";

	@Before
	public void setUp() {
		eventRepository = new EventMongoRepository(new MongoClient("localhost"), BET_DB_NAME, EVENT_COLLECTION_NAME);
		for (EventModel eventModel : eventRepository.findAll()) {
			eventRepository.delete(eventModel.getHomeTeam(), eventModel.getAwayTeam(), eventModel.getOutcome());
		}
		eventController = new EventController(eventRepository, eventView);
	}

}
