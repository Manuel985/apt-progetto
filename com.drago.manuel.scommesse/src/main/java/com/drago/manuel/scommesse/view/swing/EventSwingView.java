package com.drago.manuel.scommesse.view.swing;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.drago.manuel.scommesse.controller.EventController;
import com.drago.manuel.scommesse.model.EventModel;
import com.drago.manuel.scommesse.view.EventView;

public class EventSwingView extends JFrame implements EventView {

	private static final long serialVersionUID = 1L;

	private EventController eventController;
	private JPanel contentPane;
	private JTextField homeTeamTextBox;
	private JTextField awayTeamTextBox;
	private JTextField outcomeTextBox;
	private JTextField oddsTextBox;
	private JLabel lblHomeTeam = new JLabel("Home team");
	private JLabel lblAwayTeam = new JLabel("Away team");
	private JLabel lblOutcome = new JLabel("Outcome");
	private JLabel lblOdds = new JLabel("Odds");
	private JButton btnAdd = new JButton("Add");
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnChangeOdds = new JButton("Change Odds");
	private JButton btnDelete = new JButton("Delete");
	private JLabel errorMessageLabel = new JLabel(" ");
	private JList<EventModel> listEvents;
	private DefaultListModel<EventModel> listEventsModel;

	DefaultListModel<EventModel> getListEventsModel() {
		return listEventsModel;
	}

	public void setEventController(EventController eventController) {
		this.eventController = eventController;

	}

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

	public EventSwingView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

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

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);

		listEventsModel = new DefaultListModel<>();
		listEvents = new JList<>(listEventsModel);
		listEvents.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				EventModel event = (EventModel) value;
				return super.getListCellRendererComponent(list, getDisplayString(event), index, isSelected,
						cellHasFocus);
			}
		});
		listEvents.addListSelectionListener(e -> btnDelete.setEnabled(listEvents.getSelectedIndex() != -1));
		listEvents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listEvents.setName("eventList");
		scrollPane.setViewportView(listEvents);

		btnChangeOdds.setEnabled(false);
		GridBagConstraints gbc_btnChangeOdds = new GridBagConstraints();
		gbc_btnChangeOdds.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeOdds.gridx = 1;
		gbc_btnChangeOdds.gridy = 7;
		contentPane.add(btnChangeOdds, gbc_btnChangeOdds);
		KeyAdapter btnChangeEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				enableButtonChangeOdds();
			}
		};
		oddsTextBox.addKeyListener(btnChangeEnabler);
		listEvents.addListSelectionListener(e -> enableButtonChangeOdds());

		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 7;
		contentPane.add(btnDelete, gbc_btnDelete);

		errorMessageLabel.setName("errorMessageLabel");
		GridBagConstraints gbc_errorMessageLabel = new GridBagConstraints();
		gbc_errorMessageLabel.gridwidth = 4;
		gbc_errorMessageLabel.gridx = 0;
		gbc_errorMessageLabel.gridy = 8;
		contentPane.add(errorMessageLabel, gbc_errorMessageLabel);
	}

	private void enableButtonChangeOdds() {
		if (!oddsTextBox.getText().trim().isEmpty() && !listEvents.isSelectionEmpty())
			btnChangeOdds.setEnabled(true);
		else
			btnChangeOdds.setEnabled(false);

	}

	@Override
	public void showAllEvents(List<EventModel> eventModels) {
		eventModels.stream().forEach(listEventsModel::addElement);

	}

	@Override
	public void showError(String message, EventModel eventModel) {
		errorMessageLabel.setText(message + ": " + getDisplayString(eventModel));

	}

	@Override
	public void eventAdded(EventModel eventModel) {
		listEventsModel.addElement(eventModel);
		resetErrorLabel();

	}

	@Override
	public void eventRemoved(EventModel eventModel) {
		listEventsModel.removeElement(eventModel);
		resetErrorLabel();

	}

	@Override
	public void eventChanged(EventModel eventModel, double odds) {
		int index = listEventsModel.indexOf(eventModel);
		listEventsModel.setElementAt(
				new EventModel(eventModel.getHomeTeam(), eventModel.getAwayTeam(), eventModel.getOutcome(), odds),
				index);

	}

	private String getDisplayString(EventModel eventModel) {
		return eventModel.getHomeTeam() + " - " + eventModel.getAwayTeam() + " = " + eventModel.getOutcome() + " - "
				+ eventModel.getOdds();
	}

	private void resetErrorLabel() {
		errorMessageLabel.setText(" ");
	}

}
