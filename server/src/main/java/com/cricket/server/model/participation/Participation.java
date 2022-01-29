package com.cricket.server.model.participation;

import com.cricket.server.model.auction.Auction;
import com.cricket.server.model.team.Team;
import com.cricket.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participation {
    @Id
    @TableGenerator(name = "participant_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="participant_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "participant_gen")
    private Integer participantId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "auction_id")
    @ManyToOne
    private Auction auction;

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    @Enumerated(EnumType.STRING)
    private ParticipationType participationType;
}
