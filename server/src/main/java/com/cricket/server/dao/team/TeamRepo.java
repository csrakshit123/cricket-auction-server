package com.cricket.server.dao.team;

import com.cricket.server.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
    @Query("select (count(t) > 0) from Team t where t.teamId = :teamId and t.auction.auctionId = :auctionId")
    boolean existsTeamInAuction(@Param("teamId") Integer teamId, @Param("auctionId") Integer auctionId);

    @Query("select t from Team t where t.auction.auctionId = :auctionId")
    List<Team> findAllTeamsInAuction(@Param("auctionId") Integer auctionId);

    @Query("select t from Team t where t.teamId = :teamId and t.auction.auctionId = :auctionId")
    Optional<Team> findTeamInAuction(@Param("teamId") Integer teamId, @Param("auctionId") Integer auctionId);
}
