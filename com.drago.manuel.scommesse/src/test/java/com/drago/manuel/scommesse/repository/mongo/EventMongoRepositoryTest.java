package com.drago.manuel.scommesse.repository.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.net.InetSocketAddress;

import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		eventMongoRepository = new EventMongoRepository(client);
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

}
