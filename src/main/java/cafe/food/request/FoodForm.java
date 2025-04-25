package cafe.food.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Getter @Setter
public class FoodForm {
    private String name;

    private int price;

    private int quantity;

    private FoodType foodType;
}
