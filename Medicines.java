package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Medicines extends CadenaDeMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public Medicines(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(MEDICINES_TAGNAME)) {
			return super.codigoComun(reader, nombre);
		}

		else {
			if (getSucesor() != null) {
				return super.leeCategoria(reader, nombre);
			} else {
				reader.skipValue();
				System.err.println(nombre + " no es una categoria processable.");
				return new StringBuffer("");
			}
		}
	}

	public String leeEntry(JsonReader reader) throws IOException {
		String medicina = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(NAME_FIELD_TAGNAME)) {
				medicina = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return medicina;
	}
}