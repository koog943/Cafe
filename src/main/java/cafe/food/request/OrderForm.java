package cafe.food.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

    private String memberName;
    private Long foodId;
    private int count;

    public String toString(String foodName) {
        return "OrderForm{" +
                "memberName='" + memberName + '\'' +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", count=" + count +
                '}';
    }
}
