package cn.wenqi.jwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wenqi
 * @since v
 */
@Data
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    private Long id;

    private String name;

    @Column(name = "create_date")
    private Date createTime;
}
