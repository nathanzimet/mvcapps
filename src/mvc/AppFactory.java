package mvc;

public interface AppFactory {

    public Model makeModel();
    public View makeView(Model m);
    String getTitle();
    String[] getHelp();
    String about();
    public String[] getEditCommands();
    public Command makeEditCommand(Model model, String type, Object source);


}
