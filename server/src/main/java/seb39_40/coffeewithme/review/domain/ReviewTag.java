package seb39_40.coffeewithme.review.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seb39_40.coffeewithme.tag.domain.Tag;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ReviewTag {
    @Id
    @Column(name = "REVIEWTAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setReview(Review review){
        this.review = review;
        if (!review.getReviewTags().contains(this)){
            review.addReviewTags(this);
        }
    }

    public void setTag(Tag tag){
        this.tag = tag;
        if (!tag.getReviewTags().contains(this)){
            tag.addReviewTag(this);
        }
    }
}
