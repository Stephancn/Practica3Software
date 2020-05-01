package paquete2;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try {
			Medicines m = new Medicines(null);
			ActiveIngredients ai = new ActiveIngredients(m);
			Physiotherapies pt = new Physiotherapies(ai);
			Inhalers i = new Inhalers(pt);
			Posologies p = new Posologies(i);
			MedicinePresentations mp = new MedicinePresentations(p);
			RescueMedicinePresentations rmp = new RescueMedicinePresentations(mp);
			UserManualPhysioSteps umps = new UserManualPhysioSteps(rmp);
			UserManualSteps ums = new UserManualSteps(umps);
			DatabaseJSonReader dbjr = new DatabaseJSonReader(ums);
			try {
				System.out.println(dbjr.parse("./datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
