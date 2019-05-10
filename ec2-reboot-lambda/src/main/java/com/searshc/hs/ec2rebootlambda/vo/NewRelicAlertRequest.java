package com.searshc.hs.ec2rebootlambda.vo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewRelicAlertRequest {
	@JsonProperty("incident_acknowledge_url")
	private String incidentAckUrl;
	@JsonProperty("targets")
	private List<NewRelicAlertTarget> targets;
}
