package inheritance_mapping;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
// InheritanceType.JOINED >> 이 annotation이 생략되면, Item table에 DTYPE column 자체가 없다. 이렇게만 되어 있으면, Item의 DTYPE column에 자식 클래스의 엔티티명이 default로 삽입된다.
// InheritanceType.SINGLE_TABLE >> 이 annotation이 생략돼도, Item table에 DTYPE column이 무조건 생성된다.
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
