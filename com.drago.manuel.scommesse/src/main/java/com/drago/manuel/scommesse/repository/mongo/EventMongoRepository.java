package com.drago.manuel.scommesse.repository.mongo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;

import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.repository.EventRepository;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class EventMongoRepository implements EventRepository {

	private static final String ODDS = "odds";
	private static final String OUTCOME = "outcome";
	private static final String AWAY_TEAM = "awayTeam";
	private static final String HOME_TEAM = "homeTeam";
	private MongoCollection<Document> eventCollection;

	public EventMongoRepository(MongoClient client, String databaseName, String collectionName) {
		eventCollection = client.getDatabase(databaseName).getCollection(collectionName);
	}

	@Override
	public List<EventModel> findAll() {
		return StreamSupport.stream(eventCollection.find().spliterator(), false).map(this::fromDocumentToEvent)
				.collect(Collectors.toList());
	}

	private EventModel fromDocumentToEvent(Document d) {
		return new EventModel(d.getString(HOME_TEAM), d.getString(AWAY_TEAM), d.getString(OUTCOME),
				d.getDouble(ODDS));
	}

	@Override
	public EventModel findByHomeAwayOutcome(String homeTeam, String awayTeam, String outcome) {
		Document d = eventCollection.find(Filters.and(Filters.eq(HOME_TEAM, homeTeam),
				Filters.eq(AWAY_TEAM, awayTeam), Filters.eq(OUTCOME, outcome))).first();
		if (d != null)
			return fromDocumentToEvent(d);
		return null;
	}

	@Override
	public void save(EventModel eventModel) {
		eventCollection.insertOne(
				new Document().append(HOME_TEAM, eventModel.getHomeTeam()).append(AWAY_TEAM, eventModel.getAwayTeam())
						.append(OUTCOME, eventModel.getOutcome()).append(ODDS, eventModel.getOdds()));

	}

	@Override
	public void delete(String homeTeam, String awayTeam, String outcome) {
		eventCollection.deleteOne(Filters.and(Filters.eq(HOME_TEAM, homeTeam), Filters.eq(AWAY_TEAM, awayTeam),
				Filters.eq(OUTCOME, outcome)));

	}

	@Override
	public void update(EventModel eventModel) {
		eventCollection.updateOne(Filters.and(Filters.eq(HOME_TEAM, eventModel.getHomeTeam()),
				Filters.eq(AWAY_TEAM, eventModel.getAwayTeam()), Filters.eq(OUTCOME, eventModel.getOutcome())),
				Updates.set(ODDS, eventModel.getOdds()));

	}

}
