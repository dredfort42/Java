package edu.school21.tanks.repositories;

import edu.school21.tanks.models.TanksGame;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class TanksGameRepositoryImpl implements TanksGameRepository {
	private final JdbcTemplate jdbcTemplate;

	public TanksGameRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final RowMapper<TanksGame> rowMapper = (rs, rowNum) ->
		new TanksGame(rs.getLong("identifier"));

	@Override
	public Optional<TanksGame> findById(Long id) {
		String query = "SELECT * FROM tanks_game WHERE identifier = ?";
		List<TanksGame> list = this.jdbcTemplate.query(query, this.rowMapper, id);

		if (!list.isEmpty()) {
			return Optional.of(list.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<TanksGame> findAll() {
		String query = "SELECT * FROM tanks_game";

		return this.jdbcTemplate.query(query, this.rowMapper);
	}

	@Override
	public void save(TanksGame tanksGame) {
		String query = "INSERT INTO tanks_game DEFAULT VALUES";

		if (this.jdbcTemplate.update(query) == 0) {
			throw new RuntimeException("TanksGame was not saved");
		}
	}

	@Override
	public void update(TanksGame tanksGame) {}

	@Override
	public void delete(Long id) {
		String query = "DELETE FROM tanks_game WHERE identifier = ?";

		if (this.jdbcTemplate.update(query, id) == 0) {
			throw new RuntimeException("TanksGame was not deleted");
		}
	}

	@Override
	public Long saveGetKey(TanksGame tanksGame) {
		String query = "INSERT INTO tanks_game DEFAULT VALUES";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		if (this.jdbcTemplate.update(connection -> connection.prepareStatement(query), keyHolder) == 0) {
			throw new RuntimeException("TanksGame was not saved");
		}

		return (Long) keyHolder.getKey();
	}
}
