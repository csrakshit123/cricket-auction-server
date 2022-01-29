package com.cricket.server.model.auction;

import com.cricket.server.model.participation.Participation;
import com.cricket.server.model.team.Team;
import com.cricket.server.model.pool.Pool;
import com.cricket.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auction {
    @Id
    @TableGenerator(name = "auction_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="auction_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "auction_gen")
    private Integer auctionId;

    private String name;

    private double maxPurse;

    @Temporal(TemporalType.DATE)
    private Date auctionDate;
    private Time startTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private List<Pool> auctionPools = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private List<Team> teams = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private List<Participation> participationList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private List<AuctionManager> auctionManagerList = new ArrayList<>();
}
