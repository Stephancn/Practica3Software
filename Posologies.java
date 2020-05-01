package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Posologies extends CadenaDeMando {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public Posologies(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(POSOLOGIES_TAGNAME)) {
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
		String nombrePos = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(DESCRIPTION_FIELD_TAGNAME)) {
				nombrePos = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return nombrePos;
	}
}