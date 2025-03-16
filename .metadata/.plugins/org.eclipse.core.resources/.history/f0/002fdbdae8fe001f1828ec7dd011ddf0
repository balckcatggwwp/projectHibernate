package tw.black.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity@Table(name = "stock")
public class Stock {
	
	@Id @Column(name = "STOCKID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockid;
	@Column(name = "STOCKNAME")
	private String stockname;
	@Column(name = "STOCKCODE")
	private String stockcode ;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "stock",cascade = CascadeType.ALL)
	private List<StockTransaction> stockTransactions;
	
	public Stock() {
	
	}

	public Stock(String stockname, String stockcode) {
		super();
		this.stockname = stockname;
		this.stockcode = stockcode;
	}

	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public List<StockTransaction> getStockTransactions() {
		return stockTransactions;
	}

	public void setStockTransactions(List<StockTransaction> stockTransactions) {
		this.stockTransactions = stockTransactions;
	}
	
	
	
	

}
