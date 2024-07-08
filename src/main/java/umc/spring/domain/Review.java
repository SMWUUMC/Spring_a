package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(columnDefinition = "text")
    private String title;
    //private String body; ???

    private Float score;

    /////////

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    /////////
    // @OneToMany
    // review_image

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList;

    public void setMember(Member member){
        if(this.member != null){
            member.getReviewList().remove(this);
        }

        this.member = member;

        member.getReviewList().add(this);
    }

    public void setStore(Store store){
        // 오타......???
        //if(this.score != null)
        if(this.store != null) {
            store.getReviewList().remove(this);
        }

        this.store = store;

        store.getReviewList().add(this);
    }

}
