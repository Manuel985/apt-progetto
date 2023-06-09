package com.drago.manuel.scommesse.view;

import java.util.List;

import com.drago.manuel.scommesse.model.EventModel;

public interface EventView {

	void showAllEvents(List<EventModel> eventModels);

	void eventAdded(EventModel eventModel);

	void eventRemoved(EventModel eventModel);

	void eventChanged(EventModel eventModelToChange, double newOdds);

}
