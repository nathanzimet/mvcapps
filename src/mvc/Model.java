package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    public Boolean getUnsavedChanges() {
        return true;
    }
    public String getFileName() {
        return "";
    }
    public void setFileName(String Name) {

    }
    public void setUnsavedChanges(Boolean value) {

    }

    public void changed() {
        notifySubscribers();

    }

}
