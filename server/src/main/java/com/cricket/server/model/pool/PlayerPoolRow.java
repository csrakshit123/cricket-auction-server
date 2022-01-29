package com.cricket.server.model.pool;

import com.cricket.server.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerPoolRow {
    @Id
    @TableGenerator(name = "player_pool_row_gen", table = "id_generator",
            pkColumnName = "generator_name", valueColumnName = "generator_value",
            pkColumnValue="player_pool_row_gen", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "player_pool_row_gen")
    @Column(name = "player_pool_id")
    private Integer playerPoolId;

    @JoinColumn(name = "player_id")
    @OneToOne
    private Player player;

    private double basePrice;

    @JoinColumn(name = "pool_id")
    @ManyToOne
    private Pool pool;
}
