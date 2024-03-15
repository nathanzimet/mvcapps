package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    boolean unsavedChanges = false;
    String fileName = null;

    public boolean getUnsavedChanges() {
        return this.unsavedChanges;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String Name) {
        this.fileName = Name;

    }
    public void setUnsavedChanges(boolean value) {
        this.unsavedChanges = value;
    }

    public void changed() {
        setUnsavedChanges(true);
        notifySubscribers();
    }
}
