package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualSteps extends CadenaDeMando {
	private static final String STEXT_FIELD_TAGNAME = "stepText";
	private static final String USERMANUAL_TAGNAME = "userManualSteps";
	private static final String STITLE_FIELD_TAGNAME = "stepTitle";
	private static final String SIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualSteps(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(USERMANUAL_TAGNAME)) {
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
		String titulo = null;
		String imagen = null;
		String texto = null;
		String refInhalador = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(STITLE_FIELD_TAGNAME)) {
				titulo = reader.nextString();
			} else if (nombre.equals(SIMAGE_FIELD_TAGNAME)) {
				imagen = reader.nextString();
			} else if (nombre.equals(INHREF_FIELD_TAGNAME)) {
				refInhalador = reader.nextString();
			} else if (nombre.equals(STEXT_FIELD_TAGNAME)) {
				texto = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return titulo + FIELD_SEPARATOR + " " + imagen + FIELD_SEPARATOR + " " + texto + FIELD_SEPARATOR + " "
				+ refInhalador;
	}
}