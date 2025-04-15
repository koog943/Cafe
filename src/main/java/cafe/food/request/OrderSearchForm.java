package cafe.food.request;

import cafe.food.domain.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearchForm {
    private String memberName;
    private Status status;

}
