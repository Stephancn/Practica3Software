package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends CadenaDeMando {
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = ";";

	public Physiotherapies(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(PHYSIOTHERAPIES_TAGNAME)) {
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
		String nombrePhy = null;
		String imagen = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(NAME_FIELD_TAGNAME)) {
				nombrePhy = reader.nextString();
			} else if (nombre.equals(IMAGE_FIELD_TAGNAME)) {
				imagen = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return nombrePhy + FIELD_SEPARATOR + " " + imagen;
	}
}