package Transaction.example.SpringbootTransaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	private int id;
	private int productId;
	private int Quatity;
	private double totalprice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuatity() {
		return Quatity;
	}

	public void setQuatity(int quatity) {
		Quatity = quatity;
	}

	public Order(int id, int productId, int quatity, double totalprice) {
		super();
		this.id = id;
		this.productId = productId;
		Quatity = quatity;
		this.totalprice = totalprice;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", productId=" + productId + ", Quatity=" + Quatity + ", totalprice=" + totalprice
				+ "]";
	}

}
