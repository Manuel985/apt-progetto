package com.drago.manuel.scommesse.view.swing;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.view.EventView;

public class EventSwingView extends JFrame implements EventView {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EventController eventController;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventSwingView frame = new EventSwingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EventSwingView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	@Override
	public void showAllEvents(List<EventModel> eventModels) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showError(String message, EventModel eventModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventAdded(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventRemoved(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventChanged(EventModel eventModel) {
		// TODO Auto-generated method stub

	}

	public void setEventController(EventController eventController) {
		this.eventController = eventController;
		
	}

}
