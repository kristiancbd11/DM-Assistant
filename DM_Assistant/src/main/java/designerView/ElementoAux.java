package designerView;

public class ElementoAux {

    private static int idAux = 900;
    private int id;
    private String token;
    private String path;

    public ElementoAux(String token, String tipo) {
        this.id = idAux;
        this.path = tipo;
        idAux = idAux + 1;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return token;
	} 
	
	
    
}
