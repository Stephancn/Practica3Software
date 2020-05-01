package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MedicinePresentations extends CadenaDeMando {
	private static final String MEDPRESENTATION_TAGNAME = "medicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";
	private static final String FIELD_SEPARATOR = ";";

	public MedicinePresentations(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(MEDPRESENTATION_TAGNAME)) {
			return super.codigoComun(reader, nombre);
		}

		else {
			if (getSucesor() != null) {
				return super.leeCategoria(reader, nombre);
			} else {
				reader.skipValue();
				System.err.println(nombre + " no es una categoria procesable.");
				return new StringBuffer("");
			}
		}
	}

	private boolean esArray(JsonReader reader) throws IOException {
		Boolean esArray = false;
		if (reader.peek() == JsonToken.BEGIN_ARRAY) {
			esArray = true;
		}
		return esArray;
	}

	public String leeEntry(JsonReader reader) throws IOException {
		String refInhalador = "";
		String dose = "";
		String refPosologia = "";
		String refMedicina = null;
		String refIngredienteAct = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(MEDREF_FIELD_TAGNAME)) {
				refMedicina = reader.nextString();
			} else if (nombre.equals(ACTINGREF_FIELD_TAGNAME)) {
				refIngredienteAct = reader.nextString();
			} else if (nombre.equals(INHREF_FIELD_TAGNAME)) {
				if (!esArray(reader)) {
					refInhalador = reader.nextString();
				} else if (esArray(reader)) {
					reader.beginArray();
					while (reader.hasNext()) {
						refInhalador = refInhalador + reader.nextString() + ", ";
					}
					reader.endArray();
					refInhalador = refInhalador.substring(0, refInhalador.length() - 2);
				} else {
					refInhalador = "ERROR";
				}
			} else if (nombre.equals(DOSE_FIELD_TAGNAME)) {
				if (!esArray(reader)) {
					dose = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						dose = dose + "(" + reader.nextString() + "), ";
					}
					reader.endArray();
					dose = dose.substring(0, dose.length() - 2);
				}
			} else if (nombre.equals(POSREF_FIELD_TAGNAME)) {
				if (!esArray(reader)) {
					refPosologia = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						refPosologia = refPosologia + reader.nextString() + ", ";
					}
					reader.endArray();
					refPosologia = refPosologia.substring(0, refPosologia.length() - 2);
				}
			} else {
				reader.skipValue();
			}
		}
		return refMedicina + FIELD_SEPARATOR + " " + refIngredienteAct + FIELD_SEPARATOR + " " + refInhalador
				+ FIELD_SEPARATOR + dose + FIELD_SEPARATOR + " " + refPosologia;
	}

}
