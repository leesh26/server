package seb39_40.coffeewithme.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seb39_40.coffeewithme.review.domain.Review;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name="user_nm")
    private String userName;
    @Column(nullable = false, name="user_pw")
    private String password;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(length = 13)
    private String mobile;
    private String roles;
    @Enumerated(value=EnumType.STRING)
    private UserStatus status;
    @Column(nullable = false, length = 50, name="reg_dt")
    private LocalDate registerDate;
    //테스트용
    @Column
    private String refresh;
    /*
    @OneToOne
    @JoinColumn(name ="user_id")
    private Image profilePhoto; //외부에서 오는 키임둥, 객체를 받아와야할까용
    */

    @Transient
    private List<Wishlist> likes=new ArrayList<>();

    @Transient //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews=new ArrayList<>();

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public enum UserStatus {
        USER_SIGNUP("signup"),
        USER_WITHDRAW("withdraw");

        @Getter
        private String status;

        UserStatus(String status) {
            this.status = status;
        }
    }


    public void addReview(Review review) {
        this.reviews.add(review);
        if (review.getUser() != this){
            review.setUser(this);
        }
    }

    public void addLike(Wishlist like) {
        this.likes.add(like);
        if (like.getUser() != this){
            like.setUser(this);
        }
    }

}
