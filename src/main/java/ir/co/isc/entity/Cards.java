package ir.co.isc.entity;

import ir.co.isc.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "Cards")
@Table(name = "t_Cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SelectBeforeUpdate
public class Cards extends BaseEntity implements Serializable {
    @Column(name = "c_cardNumber", columnDefinition = "VARCHAR(16)")
    private String cardNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "c_expirationDate")
    private Date expirationDate;
    @Column(name = "c_cvv2", columnDefinition = "VARCHAR(3)")
    private String cvv2;
    @Column(name = "c_issuerCode", columnDefinition = "VARCHAR(6)")
    private String issuerCode;
    @Column(name = "c_issuerName", columnDefinition = "VARCHAR(255)")
    private String issuerName;
    /* @ManyToOne()
     @JoinColumn(name = "c_cardStatus")
     private CategoryElement cardStatus;*/
    @Column(name = "c_cardStatus", nullable = true, columnDefinition = "tinyint(1)")
    private Boolean cardStatus;
    /* @ManyToOne()
     @JoinColumn(name = "c_cardType")
     private CategoryElement cardType;*/
    @Column(name = "c_cardType")
    private CardType cardType;
    @ManyToOne()
    @JoinColumn(name = "c_customerId")
    private Customers customers;


}
