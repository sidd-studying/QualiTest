package dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * QualiTestDAO is a class used to store the data which is shared across different BDD steps.
 *
 * @author siddharth_patil
 */
@Data
public class QualiTestDAO {
    private Map<String, Float> lowestPricedProducts = new HashMap<>();
}
