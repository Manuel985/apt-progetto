package com.drago.manuel.scommesse.repository.mongo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetSocketAddress;

import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.drago.manuel.scommesse.model.EventModel;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class EventMongoRepositoryTest {

	private static final String EVENT_COLLECTION_NAME = "event";
	private static final String BET_DB_NAME = "bet";
	private static MongoServer server;
	private static InetSocketAddress serverAddress;
	private MongoClient client;
	private EventMongoRepository eventMongoRepository;
	private MongoCollection<Document> eventCollection;

	@BeforeClass
	public static void setupServer() {
		server = new MongoServer(new MemoryBackend());
		serverAddress = server.bind();
	}

	@AfterClass
	public static void shutdownServer() {
		server.shutdown();
	}

	@Before
	public void setup() {
		client = new MongoClient(new ServerAddress(serverAddress));
		eventMongoRepository = new EventMongoRepository(client, BET_DB_NAME, EVENT_COLLECTION_NAME);
		MongoDatabase database = client.getDatabase(BET_DB_NAME);
		database.drop();
		eventCollection = database.getCollection(EVENT_COLLECTION_NAME);
	}

	@After
	public void tearDown() {
		client.close();
	}

	@Test
	public void testFindAllWhenDatabaseIsEmpty() {
		assertThat(eventMongoRepository.findAll()).isEmpty();
	}

	@Test
	public void testFindAllWhenDatabaseIsNotEmpty() {
		addTestEventToDatabase("Juventus", "Inter", "1", 1.80);
		addTestEventToDatabase("Juventus", "Inter", "X", 3.20);
		assertThat(eventMongoRepository.findAll()).containsExactly(new EventModel("Juventus", "Inter", "1", 1.80),
				new EventModel("Juventus", "Inter", "X", 3.20));
	}

	private void addTestEventToDatabase(String homeTeam, String awayTeam, String outcome, double odds) {
		eventCollection.insertOne(new Document().append("homeTeam", homeTeam).append("awayTeam", awayTeam)
				.append("outcome", outcome).append("odds", odds));
	}

}
