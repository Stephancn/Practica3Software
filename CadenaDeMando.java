package paquete2;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class CadenaDeMando {
	private CadenaDeMando sucesor;

	public CadenaDeMando(CadenaDeMando siguiente) {
		this.sucesor = siguiente;
	}

	public StringBuffer leeCategoria(JsonReader reader, String nombre) throws IOException {
		return sucesor.leeCategoria(reader, nombre);
	}

	public StringBuffer codigoComun(JsonReader reader, String nombre) throws IOException {
		StringBuffer datos = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			datos.append(leeEntry(reader)).append("\n");
			reader.endObject();
		}
		datos.append("\n");
		reader.endArray();
		return datos;
	}

	public abstract String leeEntry(JsonReader reader) throws IOException;

	public CadenaDeMando getSucesor() {
		return sucesor;
	}

}
