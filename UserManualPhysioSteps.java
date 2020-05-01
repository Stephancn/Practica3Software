package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends CadenaDeMando {
	private static final String SIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYREF_FIELD_TAGNAME = "physioRef";
	private static final String USERMANUALPHY_TAGNAME = "userManualPhysioSteps";
	private static final String STITLE_FIELD_TAGNAME = "stepTitle";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualPhysioSteps(CadenaDeMando sucesor) {
		super(sucesor);
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		if (nombre.equals(USERMANUALPHY_TAGNAME)) {
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
		String imagen = null;
		String texto = null;
		String refPhisio = null;
		String titulo = null;
		while (reader.hasNext()) {
			String nombre = reader.nextName();
			if (nombre.equals(STITLE_FIELD_TAGNAME)) {
				titulo = reader.nextString();
			} else if (nombre.equals(PHYREF_FIELD_TAGNAME)) {
				refPhisio = reader.nextString();
			} else if (nombre.equals(SIMAGE_FIELD_TAGNAME)) {
				imagen = reader.nextString();
			} else if (nombre.equals(STEXT_FIELD_TAGNAME)) {
				texto = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return titulo + FIELD_SEPARATOR + " " + imagen + FIELD_SEPARATOR + " " + texto + FIELD_SEPARATOR + " "
				+ refPhisio;
	}
}