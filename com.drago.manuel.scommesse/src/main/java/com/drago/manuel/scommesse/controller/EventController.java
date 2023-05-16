package com.drago.manuel.scommesse.controller;

import com.drago.manuel.scommesse.repository.EventRepository;
import com.drago.manuel.scommesse.view.EventView;

public class EventController {

	private EventRepository eventRepository;
	private EventView eventView;

	public EventController(EventRepository eventRepository, EventView eventView) {
		this.eventRepository = eventRepository;
		this.eventView = eventView;
	}

	public void allEvents() {
		eventView.showAllEvents(eventRepository.findAll());
	}

}
