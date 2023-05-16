package com.drago.manuel.scommesse.controller;

import com.drago.manuel.scommesse.model.EventModel;
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

	public void newEvent(EventModel eventModel) {
		EventModel existingEventModel = eventRepository.findByHomeAwayOutcome(eventModel.getHomeTeam(),
				eventModel.getAwayTeam(), eventModel.getOutcome());
		if (existingEventModel != null) {
			eventView.showError("Already existing event " + eventModel.getHomeTeam() + "-" + eventModel.getAwayTeam()
					+ " " + eventModel.getOutcome(), existingEventModel);
			return;
		}

		eventRepository.save(eventModel);
		eventView.eventAdded(eventModel);

	}

	public void deleteEvent(EventModel eventModel) {
		eventRepository.delete(eventModel.getHomeTeam(), eventModel.getAwayTeam(), eventModel.getOutcome());
		eventView.eventRemoved(eventModel);

	}

}
