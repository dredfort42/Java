package edu.school21.tanks.repositories;

import edu.school21.tanks.models.TanksGame;
import edu.school21.tanks.models.TanksStats;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class TanksStatsRepositoryImpl implements TanksStatsRepository {
	private final JdbcTemplate jdbcTemplate;

	public TanksStatsRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final RowMapper<TanksStats> rowMapper = (rs, rowNum) ->
		new TanksStats(rs.getLong("identifier"), new TanksGame(rs.getLong("game")),
				rs.getString("nickname"), rs.getInt("shots"), rs.getInt("hits"),
				rs.getInt("misses"));

	@Override
	public Optional<TanksStats> findById(Long id) {
		String query = "SELECT * FROM tanks_stats WHERE identifier = ?";
		List<TanksStats> list = this.jdbcTemplate.query(query, this.rowMapper, id);

		if (!list.isEmpty()) {
			return Optional.of(list.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<TanksStats> findAll() {
		String query = "SELECT * FROM tanks_stats";

		return this.jdbcTemplate.query(query, this.rowMapper);
	}

	@Override
	public void save(TanksStats tanksStats) {
		String query = "INSERT INTO tanks_stats (game, nickname, shots, hits, misses) VALUES (?, ?, ?, ?, ?)";

		if (this.jdbcTemplate.update(
				query,
				tanksStats.getTanksGame().getIdentifier(),
				tanksStats.getNickname(),
				tanksStats.getShots(),
				tanksStats.getHits(),
				tanksStats.getMisses()
		) == 0) {
			throw new RuntimeException("TanksStats was not saved");
		}
	}

	@Override
	public void update(TanksStats tanksStats) {
		String query = "UPDATE tanks_stats SET game = ?, nickname = ?, shots = ?, hits = ?, misses = ? WHERE identifier = ?";

		if (this.jdbcTemplate.update(query, tanksStats.getTanksGame().getIdentifier(), tanksStats.getNickname(),
				tanksStats.getShots(), tanksStats.getHits(), tanksStats.getMisses()) == 0) {
			throw new RuntimeException("TanksStats was not updated");
		}
	}

	@Override
	public void delete(Long id) {
		String query = "DELETE FROM tanks_stats WHERE identifier = ?";

		if (this.jdbcTemplate.update(query, id) == 0) {
			throw new RuntimeException("TanksStats was not deleted");
		}
	}
}
