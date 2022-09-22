package seb39_40.coffeewithme.review.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seb39_40.coffeewithme.cafe.domain.Cafe;
import seb39_40.coffeewithme.common.domain.BasicEntity;
import seb39_40.coffeewithme.user.domain.User;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Review extends BasicEntity {
    @Id @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CAFE_ID")
    private Cafe cafe;

    @Column(nullable = false)
    private Long reviewImg;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer score;

    public void setCafe(Cafe cafe){
        this.cafe = cafe;
        if (!cafe.getReviews().contains(this)){
            cafe.addReviews(this);
        }
    }

    public void setUser(User user){
        this.user = user;
        if (!user.getReviews().contains(this)){
            user.addReview(this);
        }
    }
}
