package io.onedev.server.web.component.build.perservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.onedev.server.model.support.BuildPreservation;
import io.onedev.server.web.editable.annotation.Editable;

@Editable
public class BuildPreservationsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<BuildPreservation> buildPreservations = new ArrayList<>();

	@Editable
	public List<BuildPreservation> getBuildPreservations() {
		return buildPreservations;
	}

	public void setBuildPreservations(List<BuildPreservation> buildPreservations) {
		this.buildPreservations = buildPreservations;
	}
	
}
