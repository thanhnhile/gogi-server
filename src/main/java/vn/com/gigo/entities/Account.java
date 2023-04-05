package vn.com.gigo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.Nullable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "accounts")
public class Account implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String token;
	
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Order> orderList;
	
	@Nullable
	@OneToMany(mappedBy="account",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Customer> customerList;
	

	@ManyToMany
	@JoinTable(name = "accounts_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "accounts_products", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "accounts_vouchers", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "voucher_id"))
	private Set<Voucher> vouchers = new HashSet<>();
	
	public Account() {
		super();
	}

	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public Account(String username, String password, String email, String token, List<Order> orderList,
			List<Customer> customerList, Set<Role> roles, Set<Product> products, Set<Voucher> vouchers) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.token = token;
		this.orderList = orderList;
		this.customerList = customerList;
		this.roles = roles;
		this.products = products;
		this.vouchers = vouchers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	

	public Set<Voucher> getVouchers() {
		return vouchers;
	}


	public void setVouchers(Set<Voucher> vouchers) {
		this.vouchers = vouchers;
	}




	public List<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	


	

	public List<Customer> getCustomerList() {
		return customerList;
	}




	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}


	public void addCustomer(Customer customer) {
		if(customer!=null) {
			getCustomerList().add(customer);
		}
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
