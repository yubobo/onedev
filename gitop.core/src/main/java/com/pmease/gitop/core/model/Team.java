package com.pmease.gitop.core.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;
import com.pmease.commons.hibernate.AbstractEntity;
import com.pmease.commons.validation.Name;
import com.pmease.gitop.core.permission.operation.GeneralOperation;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"owner", "name"})
})
@SuppressWarnings("serial")
public class Team extends AbstractEntity {

	@ManyToOne
	@JoinColumn(nullable=false)
	private User owner;

	@Column(nullable=false)
	private String name;
	
	private String description;
	
	@Column(nullable=false)
	private GeneralOperation authorizedOperation = GeneralOperation.READ;
	
	@OneToMany(mappedBy="team", cascade=CascadeType.REMOVE)
	private Collection<Membership> memberships = new ArrayList<Membership>();
	
	@OneToMany(mappedBy="team", cascade=CascadeType.REMOVE)
	private Collection<Authorization> authorizations = new ArrayList<Authorization>();

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Name
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GeneralOperation getAuthorizedOperation() {
		return authorizedOperation;
	}

	public void setAuthorizedOperation(GeneralOperation authorizedOeration) {
		this.authorizedOperation = authorizedOeration;
	}

	public Collection<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Collection<Membership> memberships) {
		this.memberships = memberships;
	}

	public Collection<Authorization> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(Collection<Authorization> authorizations) {
		this.authorizations = authorizations;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", getName())
				.add("owner", getOwner().getName())
				.toString();
	}

}
