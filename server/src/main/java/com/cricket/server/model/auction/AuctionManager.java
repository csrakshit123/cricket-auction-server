package com.cricket.server.model.auction;

import com.cricket.server.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionManager {
    @Id
    @TableGenerator(name = "auction_manager_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="auction_manager_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "auction_manager_gen")
    private Integer auctionManagerId;

    @JoinColumn(name = "auction_id")
    @ManyToOne
    private Auction auction;

    @JoinColumn(name = "manager_username")
    @ManyToOne
    private User manager;
}
