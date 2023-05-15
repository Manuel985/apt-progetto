package com.drago.manuel.scommesse.repository;

import java.util.List;

import com.drago.manuel.scommesse.model.EventModel;

public interface EventRepository {

	public List<EventModel> findAll();

	public EventModel findByHomeAwayOutcome(String homeTeam, String awayTeam, String outcome);

	public void save(EventModel eventModel);

	public void delete(String homeTeam, String awayTeam, String outcome);

	public void update(EventModel eventModel);

}
