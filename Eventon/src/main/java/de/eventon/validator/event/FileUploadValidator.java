package de.eventon.validator.event;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value="fileUploadValidator")
public class FileUploadValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Part file = (Part) value;
		 
        FacesMessage message=null;
 
        try {
        	System.out.println("Im Validator");
			System.out.println(file.getContentType());
            if (file==null || file.getSize()<=0 || file.getContentType().isEmpty() )
                message=new FacesMessage("Select a valid file");
            else if (!file.getContentType().endsWith("jpeg"))
                message=new FacesMessage("Select JPG file");
            else if (file.getSize()>2000000)
                 message=new FacesMessage("File size too big. File size allowed  is less than or equal to 2 MB.");
 
            if (message!=null && !message.getDetail().isEmpty())
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage("createEvent:eventPicture", message);
                }
 
        } catch (Exception ex) {
    		FacesContext.getCurrentInstance().addMessage("createEvent:eventPicture", 
    				new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
		
	}

}
