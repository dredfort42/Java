package edu.school21.tanks.repositories;

import edu.school21.tanks.models.TanksGame;

public interface TanksGameRepository extends CrudRepository<TanksGame> {
    Long saveGetKey(TanksGame tanksGame);
}
