package Model;

import javax.swing.JButton;

public class Button extends JButton {

	private String Name;
	private Graph graph;
	private int Id;
	public Button(String Name,int Objectcnt) {
		this.Name=Name;
		setText(Name);
		this.Id = Objectcnt;
		this.graph=null;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
		setText(Name);
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
}
