package by.vsu.partText;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public String getString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Component component : components) {

            if (component.getClass() == PunctuationMark.class) {
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }

            stringBuilder.append(component.getString()).append(" ");
        }

        return stringBuilder.toString();
    }
}
