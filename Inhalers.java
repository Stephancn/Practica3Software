package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Inhalers extends CadenaDeMando {
	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEPARATOR = ";";

	public Inhalers(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(INHALERS_TAGNAME)) {
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

	public String leeEntry(JsonReader reader) throws IOException {
		String nombre1 = null;
		String imagen = null;
		while (reader.hasNext()) {
			String nombre2 = reader.nextName();
			if (nombre2.equals(NAME_FIELD_TAGNAME)) {
				nombre1 = reader.nextString();
			} else if (nombre2.equals(IMAGE_FIELD_TAGNAME)) {
				imagen = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return nombre1 + FIELD_SEPARATOR +" "+ imagen;
	}
}