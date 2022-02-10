



public class Book {

	private int id;
	private String name;
	private String tags;
	private String locate;
	private int host;
	private int year;
	private String author;

	public Book(int id, String name, String tags, String locate, int host, int year, String author){
		this.id = id;
		this.name = name;
		this.tags = tags;
		this.locate = locate;
		this.host = host;
		this.year = year;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTags() {
		return tags;
	}

	public String getLocate() {
		return locate;
	}

	public int getHost() {
		return host;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	@Override
	public String toString() {
		return "id=" + id + "  name='" + name  + "  tags='" + tags + "  host='" + host + "\n";
	}*/

	@Override
	public String toString() {
		return "id=" + id + "  name='" + name  + "  tags='" + tags + "  host='" + host + "\n";
	}
}
