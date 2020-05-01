package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescueMedicinePresentations extends CadenaDeMando {
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = ";";

	public RescueMedicinePresentations(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(RESCUEMEDPRES_TAGNAME)) {
			return super.codigoComun(reader, nombre);
		} 
		
		else {
			if (getSucesor() != null) {
				return super.leeCategoria(reader, nombre);
			} else {
				reader.skipValue();
				System.err.println(nombre + "no es una categoria procesable.");
				return new StringBuffer("");
			}
		}
	}

	public String leeEntry(JsonReader reader) throws IOException {
		String refInhalador = null;
		String dose = null;
		String refMedica = null;
		String refIngredienteAct = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(MEDREF_FIELD_TAGNAME)) {
				refMedica = reader.nextString();
			} else if (nombre.equals(INHREF_FIELD_TAGNAME)) {
				refInhalador = reader.nextString();
			} else if (nombre.equals(ACTINGREF_FIELD_TAGNAME)) {
				refIngredienteAct = reader.nextString();
			} else if (nombre.equals(DOSE_FIELD_TAGNAME)) {
				dose = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return refMedica + FIELD_SEPARATOR + " " + refIngredienteAct + FIELD_SEPARATOR + "" + refInhalador
				+ FIELD_SEPARATOR + "" + dose;
	}
}