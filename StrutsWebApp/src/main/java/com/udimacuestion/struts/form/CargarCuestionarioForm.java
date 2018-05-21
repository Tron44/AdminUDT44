package com.udimacuestion.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 
 * @author rarranz
 *
 */
public class CargarCuestionarioForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	// identificadores del tratamiento de formulario para la gestión de upload de ficheros
	private FormFile theFile;
	protected String filePath;

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

}
