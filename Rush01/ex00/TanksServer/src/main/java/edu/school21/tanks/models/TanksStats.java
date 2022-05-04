package edu.school21.tanks.models;

import java.util.Objects;

public class TanksStats {
	private Long identifier;
	private TanksGame tanksGame;
	private String nickname;
	private Integer shots;
	private Integer hits;
	private Integer misses;

	{
		this.identifier = null;
		this.tanksGame = new TanksGame();
		this.nickname = null;
		this.shots = null;
		this.hits = null;
		this.misses = null;
	}

	public TanksStats() {}

	public TanksStats(TanksGame tanksGame, String nickname, Integer shots, Integer hits) {
		this.tanksGame = tanksGame;
		this.nickname = nickname;
		this.shots = shots;
		this.hits = hits;
		this.misses = shots - hits;
	}

	public TanksStats(Long identifier, TanksGame tanksGame, String nickname, Integer shots, Integer hits, Integer misses) {
		this.identifier = identifier;
		this.tanksGame = tanksGame;
		this.nickname = nickname;
		this.shots = shots;
		this.hits = hits;
		this.misses = misses;
	}

	public Long getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public TanksGame getTanksGame() {
		return this.tanksGame;
	}

	public void setTanksGame(TanksGame tanksGame) {
		this.tanksGame = tanksGame;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getShots() {
		return this.shots;
	}

	public void setShots(Integer shots) {
		this.shots = shots;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getMisses() {
		return this.misses;
	}

	public void setMisses(Integer misses) {
		this.shots = misses;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || this.getClass() != object.getClass()) {
			return false;
		}

		TanksStats tanksStats = (TanksStats) object;

		return Objects.equals(this.identifier, tanksStats.identifier)
				&& Objects.equals(this.tanksGame, tanksStats.tanksGame)
				&& Objects.equals(this.nickname, tanksStats.nickname)
				&& Objects.equals(this.shots, tanksStats.shots)
				&& Objects.equals(this.hits, tanksStats.hits)
				&& Objects.equals(this.misses, tanksStats.misses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.identifier, this.tanksGame, this.nickname, this.shots, this.hits, this.misses);
	}

	@Override
	public String toString() {
		return "TanksStats : { identifier=" + this.identifier + ", nickname=" + this.tanksGame + ", tanksGame="
				+ this.nickname + ", shots=" + this.shots + ", hits=" + this.hits + ", misses=" + this.misses + " }";
	}
}
