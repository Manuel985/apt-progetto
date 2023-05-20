package com.drago.manuel.scommesse.app.swing;

import java.awt.EventQueue;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.repository.mongo.EventMongoRepository;
import com.drago.manuel.scommesse.view.swing.EventSwingView;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * Communicates with a MongoDB server on localhost; start MongoDB with Docker with
 * 
 * <pre>
 * docker run -p 27017:27017 --rm mongo:4.4.3
 * </pre>
 * 
 * @author Lorenzo Bettini
 *
 */
public class EventSwingApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				String mongoHost = "localhost";
				int mongoPort = 27017;
				if (args.length > 0)
					mongoHost = args[0];
				if (args.length > 1)
					mongoPort = Integer.parseInt(args[1]);
				EventMongoRepository eventMongoRepository = new EventMongoRepository(
						new MongoClient(new ServerAddress(mongoHost, mongoPort)), "bet", "event");
				EventSwingView eventSwingView = new EventSwingView();
				EventController eventController = new EventController(eventMongoRepository, eventSwingView);
				eventSwingView.setEventController(eventController);
				eventSwingView.setVisible(true);
				eventController.allEvents();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
