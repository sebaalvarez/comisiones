package sec.comisiones.vistas;

import java.util.Locale;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.server.UserError;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


public interface ErrorDisplay {
    void setError(String error);
    void clearError();
}


    
@SuppressWarnings("serial")
class ErrorLabel extends Label implements ErrorDisplay {
    public ErrorLabel() {
        setVisible(false);
    }
    
    public void setError(String error) {
        setValue(error);
        setComponentError(new UserError(error));
        setVisible(true);
    }

    public void clearError() {
        setValue(null);
        setComponentError(null);
        setVisible(false);
    }
}

@SuppressWarnings("serial")
class ErrorfulFieldGroup extends FieldGroup {
    
    private ErrorDisplay errorDisplay;

	public ErrorfulFieldGroup(Item item) {
        super(item);
    }



	public void setErrorDisplay(ErrorDisplay errorDisplay) {
        this.errorDisplay = errorDisplay; 
    }
    
    @Override
    public void commit() throws CommitException {
        try {
            super.commit();
            UI.getCurrent().setLocale(new Locale("es"));
            if (errorDisplay != null)
                errorDisplay.clearError();
        } catch (CommitException e) {
            if (errorDisplay != null)
                errorDisplay.setError(e.getCause().getMessage());
            	Notification.show("Existen errores en las validaciones de los datos. ", Notification.Type.ERROR_MESSAGE );
            throw e;
        }
    }
}
    
