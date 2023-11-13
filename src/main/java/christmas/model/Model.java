package christmas.model;

import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<Object,Object> model;

    public Model() {
        this.model = new HashMap<>();
    }

    public void addAttribute(Object key, Object value){
        model.put(key,value);
    }

    public Map<Object, Object> getModel() {
        return model;
    }
}
