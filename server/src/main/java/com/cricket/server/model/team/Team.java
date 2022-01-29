package com.cricket.server.model.team;

import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.participation.Participation;
import com.cricket.server.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @TableGenerator(name = "team_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="team_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "team_gen")
    @Column(name = "team_id")
    @Id
    private Integer teamId;

    private String name;

    private double remainingPurse;

    @JoinTable(name = "team_players_table",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "player_id")}
    )
    @OneToMany
    private List<Player> players = new ArrayList<>();

    @JoinColumn(name = "auction_id")
    @ManyToOne
    private Auction auction;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Participation> participationList = new ArrayList<>();
}
