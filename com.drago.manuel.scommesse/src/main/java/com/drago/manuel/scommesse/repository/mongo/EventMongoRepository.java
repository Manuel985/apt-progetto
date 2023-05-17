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

public class EventMongoRepository implements EventRepository {

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
		return new EventModel(d.getString("homeTeam"), d.getString("awayTeam"), d.getString("outcome"),
				d.getDouble("odds"));
	}

	@Override
	public EventModel findByHomeAwayOutcome(String homeTeam, String awayTeam, String outcome) {
		Document d = eventCollection.find(Filters.and(Filters.eq("homeTeam", homeTeam),
				Filters.eq("awayTeam", awayTeam), Filters.eq("outcome", outcome))).first();
		if (d != null)
			return fromDocumentToEvent(d);
		return null;
	}

	@Override
	public void save(EventModel eventModel) {
		eventCollection.insertOne(
				new Document().append("homeTeam", eventModel.getHomeTeam()).append("awayTeam", eventModel.getAwayTeam())
						.append("outcome", eventModel.getOutcome()).append("odds", eventModel.getOdds()));

	}

	@Override
	public void delete(String homeTeam, String awayTeam, String outcome) {
		eventCollection.deleteOne(Filters.and(Filters.eq("homeTeam", homeTeam), Filters.eq("awayTeam", awayTeam),
				Filters.eq("outcome", outcome)));

	}

	@Override
	public void update(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

}
