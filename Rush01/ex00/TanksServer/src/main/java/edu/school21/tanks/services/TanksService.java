package edu.school21.tanks.services;

import edu.school21.tanks.models.TanksStats;

public interface TanksService {
	Long saveGame();
	void saveStats(Long tanksGameIdentifier, String nickname, Integer shots, Integer hits);
}
