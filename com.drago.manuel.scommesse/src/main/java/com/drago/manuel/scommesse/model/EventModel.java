package com.drago.manuel.scommesse.model;

import java.util.Objects;

public class EventModel {

	private String homeTeam;
	private String awayTeam;
	private String outcome;
	private double odds;

	public EventModel() {

	}

	public EventModel(String homeTeam, String awayTeam, String outcome, double odds) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.outcome = outcome;
		this.odds = odds;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public double getOdds() {
		return odds;
	}

	public void setOdds(double odds) {
		this.odds = odds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(awayTeam, homeTeam, odds, outcome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventModel other = (EventModel) obj;
		return Objects.equals(awayTeam, other.awayTeam) && Objects.equals(homeTeam, other.homeTeam)
				&& Double.doubleToLongBits(odds) == Double.doubleToLongBits(other.odds)
				&& Objects.equals(outcome, other.outcome);
	}

	@Override
	public String toString() {
		return "EventModel [homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", outcome=" + outcome + ", odds=" + odds
				+ "]";
	}

}
