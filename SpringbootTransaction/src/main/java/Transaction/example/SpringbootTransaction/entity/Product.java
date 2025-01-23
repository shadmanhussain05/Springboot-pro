package Transaction.example.SpringbootTransaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PRODUCTS")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	public Product(int i, String string, double d, int j) {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
	private Double Price;
	private int Stockquantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public int getStockquantity() {
		return Stockquantity;
	}
	public void setStockquantity(int stockquantity) {
		Stockquantity = stockquantity;
	}
	public Product(int id, String name, Double price, int stockquantity) {
		super();
		this.id = id;
		Name = name;
		Price = price;
		Stockquantity = stockquantity;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", Name=" + Name + ", Price=" + Price + ", Stockquantity=" + Stockquantity + "]";
	}
	
	
}
