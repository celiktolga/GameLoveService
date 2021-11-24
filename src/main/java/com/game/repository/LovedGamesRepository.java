package com.game.repository;

import com.game.model.Game;
import com.game.model.LovedGames;
import com.game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LovedGamesRepository extends JpaRepository<LovedGames, Long> {
    List<LovedGames> findAllByGameAndPlayer(Game game, Player player);

    List<LovedGames> findAllByPlayer(Player player);

////    @Query("select max (lg.game.id) as count,lg.game.id from LovedGames lg group by lg.game.id")
//    @Query(name = " select id  from (SELECT   FK_GAME as ıd , COUNT(*)  as num FROM LOVED_GAMES GROUP BY FK_GAME  order by num desc )where rownum=1",nativeQuery = true)
//    @Query("select  max (lg.game) from LovedGames lg join lg.game gm group by gm order by max (lg.game)")
//    List<Game> findByParams();
}
