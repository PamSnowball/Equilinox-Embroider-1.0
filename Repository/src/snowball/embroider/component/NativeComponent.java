package snowball.embroider.component;

public abstract class NativeComponent implements IComponent {
    @Override
    public String name() {
        Class<?> clazz = this.getClass();
        while (clazz.getSuperclass() != null && clazz.getSuperclass() != NativeComponent.class) {
            clazz = clazz.getSuperclass();
        }

        return clazz.getName();
    }
}
