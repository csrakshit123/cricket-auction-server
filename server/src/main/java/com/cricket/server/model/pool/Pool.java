package com.cricket.server.model.pool;

import com.cricket.server.model.auction.Auction;
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
public class Pool {
    @Id
    @TableGenerator(name = "categorypool_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="categorypool_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "categorypool_gen")
    @Column(name = "pool_id", nullable = false)
    private Integer poolId;

    private String name;

    @OneToMany(mappedBy = "pool", cascade = CascadeType.ALL)
    private List<PlayerPoolRow> playerPool = new ArrayList<>();

    @JoinColumn(name = "auction_id")
    @ManyToOne
    private Auction auction;
}


