package com.drago.manuel.scommesse.app.swing;

import java.awt.EventQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.repository.mongo.EventMongoRepository;
import com.drago.manuel.scommesse.view.swing.EventSwingView;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Communicates with a MongoDB server on localhost; start MongoDB with Docker
 * with
 * 
 * <pre>
 * docker run -p 27017:27017 --rm mongo:4.4.3
 * </pre>
 * 
 * @author Manuel Drago
 *
 */
@Command(mixinStandardHelpOptions = true)
public class EventSwingApp implements Callable<Void> {

	@Option(names = { "--mongo-host" }, description = "MongoDB host address")
	private String mongoHost = "localhost";

	@Option(names = { "--mongo-port" }, description = "MongoDB host port")
	private int mongoPort = 27017;

	@Option(names = { "--db-name" }, description = "Database name")
	private String databaseName = "bet";

	@Option(names = { "--db-collection" }, description = "Collection name")
	private String collectionName = "event";

	public static void main(String[] args) {
		new CommandLine(new EventSwingApp()).execute(args);
	}

	@Override
	public Void call() throws Exception {
		EventQueue.invokeLater(() -> {
			try {
				EventMongoRepository eventMongoRepository = new EventMongoRepository(
						new MongoClient(new ServerAddress(mongoHost, mongoPort)), databaseName, collectionName);
				EventSwingView eventSwingView = new EventSwingView();
				EventController eventController = new EventController(eventMongoRepository, eventSwingView);
				eventSwingView.setEventController(eventController);
				eventSwingView.setVisible(true);
				eventController.allEvents();
			} catch (Exception e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
			}
		});
		return null;
	}

}
