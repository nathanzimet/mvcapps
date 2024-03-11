package mvc;

public abstract class Command {
    public Model model;

    public Command(Model model) {
        this.model = model;
    }

    public void execute() {
        System.out.println("trying to execute: im in command ");

    }
}
