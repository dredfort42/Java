package edu.school21.tanks.services;

import edu.school21.tanks.models.TanksGame;
import edu.school21.tanks.models.TanksStats;
import edu.school21.tanks.repositories.TanksGameRepository;
import edu.school21.tanks.repositories.TanksStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TanksServiceImpl implements TanksService {
	private final TanksGameRepository tanksGameRepository;
	private final TanksStatsRepository tanksStatsRepository;

	@Autowired
	public TanksServiceImpl(TanksGameRepository tanksGameRepository, TanksStatsRepository tanksStatsRepository) {
		this.tanksGameRepository = tanksGameRepository;
		this.tanksStatsRepository = tanksStatsRepository;
	}

	@Override
	public Long saveGame() {
		return this.tanksGameRepository.saveGetKey(new TanksGame());
	}

	@Override
	public void saveStats(Long tanksGameIdentifier, String nickname, Integer shots, Integer hits) {
		this.tanksStatsRepository.save(new TanksStats(new TanksGame(tanksGameIdentifier), nickname, shots, hits));
	}
}
