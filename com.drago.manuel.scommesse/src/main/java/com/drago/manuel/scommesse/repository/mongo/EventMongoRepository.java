package com.drago.manuel.scommesse.repository.mongo;

import java.util.Collections;
import java.util.List;

import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.EventRepository;
import com.mongodb.MongoClient;

public class EventMongoRepository implements EventRepository {

	public EventMongoRepository(MongoClient client) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EventModel> findAll() {
		return Collections.emptyList();
	}

	@Override
	public EventModel findByHomeAwayOutcome(String homeTeam, String awayTeam, String outcome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String homeTeam, String awayTeam, String outcome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

}
