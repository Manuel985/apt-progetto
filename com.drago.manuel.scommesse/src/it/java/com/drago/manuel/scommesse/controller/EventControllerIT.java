package com.drago.manuel.scommesse.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;

import org.bson.Document;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.containers.MongoDBContainer;

import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.EventRepository;
import com.drago.manuel.scommesse.repository.mongo.EventMongoRepository;
import com.drago.manuel.scommesse.view.EventView;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerIT {

	@Mock
	private EventView eventView;

	private EventRepository eventRepository;

	private EventController eventController;

	private MongoClient client;
	private MongoCollection<Document> eventCollection;

	private static final String BET_DB_NAME = "bet";
	private static final String EVENT_COLLECTION_NAME = "event";

	@ClassRule
	public static final MongoDBContainer mongo = new MongoDBContainer("mongo:4.4.3");

	@Before
	public void setup() {
		client = new MongoClient(new ServerAddress(mongo.getHost(), mongo.getMappedPort(27017)));
		eventRepository = new EventMongoRepository(client, BET_DB_NAME, EVENT_COLLECTION_NAME);
		MongoDatabase database = client.getDatabase(BET_DB_NAME);
		database.drop();
		eventCollection = database.getCollection(EVENT_COLLECTION_NAME);
		eventController = new EventController(eventRepository, eventView);
	}

	@Test
	public void testAllEvents() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		eventRepository.save(eventModel);
		eventController.allEvents();
		verify(eventView).showAllEvents(asList(eventModel));
	}

	@Test
	public void testNewEvent() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		eventController.newEvent(eventModel);
		verify(eventView).eventAdded(eventModel);
	}

	@Test
	public void testDeleteEvent() {
		EventModel eventModel = new EventModel("Juventus", "Inter", "X", 3.20);
		eventRepository.save(eventModel);
		eventController.deleteEvent(eventModel);
		verify(eventView).eventRemoved(eventModel);
	}

}
