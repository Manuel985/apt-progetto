package com.drago.manuel.scommesse.view.swing;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.view.EventView;

public class EventSwingView extends JFrame implements EventView {
	private EventController eventController;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField homeTeamTextBox;
	private JTextField awayTeamTextBox;
	private JTextField outcomeTextBox;
	private JTextField oddsTextBox;

	/**
	 * Launch the application.
	 */
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblHomeTeam = new JLabel("Home team");
		GridBagConstraints gbc_lblHomeTeam = new GridBagConstraints();
		gbc_lblHomeTeam.anchor = GridBagConstraints.EAST;
		gbc_lblHomeTeam.insets = new Insets(0, 0, 5, 5);
		gbc_lblHomeTeam.gridx = 0;
		gbc_lblHomeTeam.gridy = 0;
		contentPane.add(lblHomeTeam, gbc_lblHomeTeam);

		homeTeamTextBox = new JTextField();
		homeTeamTextBox.setName("homeTeamTextBox");
		GridBagConstraints gbc_homeTeamTextBox = new GridBagConstraints();
		gbc_homeTeamTextBox.gridwidth = 3;
		gbc_homeTeamTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_homeTeamTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeTeamTextBox.gridx = 1;
		gbc_homeTeamTextBox.gridy = 0;
		contentPane.add(homeTeamTextBox, gbc_homeTeamTextBox);
		homeTeamTextBox.setColumns(10);

		JLabel lblAwayTeam = new JLabel("Away team");
		GridBagConstraints gbc_lblAwayTeam = new GridBagConstraints();
		gbc_lblAwayTeam.anchor = GridBagConstraints.EAST;
		gbc_lblAwayTeam.insets = new Insets(0, 0, 5, 5);
		gbc_lblAwayTeam.gridx = 0;
		gbc_lblAwayTeam.gridy = 1;
		contentPane.add(lblAwayTeam, gbc_lblAwayTeam);

		awayTeamTextBox = new JTextField();
		awayTeamTextBox.setName("awayTeamTextBox");
		GridBagConstraints gbc_awayTeamTextBox = new GridBagConstraints();
		gbc_awayTeamTextBox.gridwidth = 3;
		gbc_awayTeamTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_awayTeamTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_awayTeamTextBox.gridx = 1;
		gbc_awayTeamTextBox.gridy = 1;
		contentPane.add(awayTeamTextBox, gbc_awayTeamTextBox);
		awayTeamTextBox.setColumns(10);

		JLabel lblOutcome = new JLabel("Outcome");
		GridBagConstraints gbc_lblOutcome = new GridBagConstraints();
		gbc_lblOutcome.anchor = GridBagConstraints.EAST;
		gbc_lblOutcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutcome.gridx = 0;
		gbc_lblOutcome.gridy = 2;
		contentPane.add(lblOutcome, gbc_lblOutcome);

		outcomeTextBox = new JTextField();
		outcomeTextBox.setName("outcomeTextBox");
		GridBagConstraints gbc_outcomeTextBox = new GridBagConstraints();
		gbc_outcomeTextBox.gridwidth = 3;
		gbc_outcomeTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_outcomeTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_outcomeTextBox.gridx = 1;
		gbc_outcomeTextBox.gridy = 2;
		contentPane.add(outcomeTextBox, gbc_outcomeTextBox);
		outcomeTextBox.setColumns(10);

		JLabel lblOdds = new JLabel("Odds");
		GridBagConstraints gbc_lblOdds = new GridBagConstraints();
		gbc_lblOdds.anchor = GridBagConstraints.EAST;
		gbc_lblOdds.insets = new Insets(0, 0, 5, 5);
		gbc_lblOdds.gridx = 0;
		gbc_lblOdds.gridy = 3;
		contentPane.add(lblOdds, gbc_lblOdds);

		oddsTextBox = new JTextField();
		oddsTextBox.setName("oddsTextBox");
		GridBagConstraints gbc_oddsTextBox = new GridBagConstraints();
		gbc_oddsTextBox.gridwidth = 3;
		gbc_oddsTextBox.insets = new Insets(0, 0, 5, 0);
		gbc_oddsTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_oddsTextBox.gridx = 1;
		gbc_oddsTextBox.gridy = 3;
		contentPane.add(oddsTextBox, gbc_oddsTextBox);
		oddsTextBox.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 4;
		contentPane.add(btnAdd, gbc_btnAdd);
		KeyAdapter btnAddEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAdd.setEnabled(!homeTeamTextBox.getText().trim().isEmpty()
						&& !awayTeamTextBox.getText().trim().isEmpty() && !outcomeTextBox.getText().trim().isEmpty()
						&& !oddsTextBox.getText().trim().isEmpty());
			}
		};
		homeTeamTextBox.addKeyListener(btnAddEnabler);
		awayTeamTextBox.addKeyListener(btnAddEnabler);
		outcomeTextBox.addKeyListener(btnAddEnabler);
		oddsTextBox.addKeyListener(btnAddEnabler);

		JList eventList = new JList();
		eventList.setName("eventList");
		GridBagConstraints gbc_eventList = new GridBagConstraints();
		gbc_eventList.gridheight = 2;
		gbc_eventList.gridwidth = 4;
		gbc_eventList.insets = new Insets(0, 0, 5, 0);
		gbc_eventList.fill = GridBagConstraints.BOTH;
		gbc_eventList.gridx = 0;
		gbc_eventList.gridy = 5;
		contentPane.add(eventList, gbc_eventList);

		JButton btnChangeOdds = new JButton("Change Odds");
		btnChangeOdds.setEnabled(false);
		GridBagConstraints gbc_btnChangeOdds = new GridBagConstraints();
		gbc_btnChangeOdds.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeOdds.gridx = 1;
		gbc_btnChangeOdds.gridy = 7;
		contentPane.add(btnChangeOdds, gbc_btnChangeOdds);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 7;
		contentPane.add(btnDelete, gbc_btnDelete);

		JLabel errorMessageLabel = new JLabel(" ");
		errorMessageLabel.setName("errorMessageLabel");
		GridBagConstraints gbc_errorMessageLabel = new GridBagConstraints();
		gbc_errorMessageLabel.gridwidth = 4;
		gbc_errorMessageLabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorMessageLabel.gridx = 0;
		gbc_errorMessageLabel.gridy = 8;
		contentPane.add(errorMessageLabel, gbc_errorMessageLabel);
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
